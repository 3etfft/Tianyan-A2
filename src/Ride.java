import java.io.*;
import java.util.*;

// 游乐设施类：实现所有接口方法
public class Ride implements RideInterface {
    // 基础属性
    private String rideName;        // 设施名称
    private int maxCapacity;        // 单周期最大载客量
    private Employee operator;      // 操作员（员工）
    private int numOfCycles = 0;    // 已运行周期数

    // Part3：排队队列（FIFO，用LinkedList实现Queue）
    private Queue<Visitor> waitingQueue = new LinkedList<>();

    // Part4A：骑行历史（LinkedList）
    private LinkedList<Visitor> rideHistory = new LinkedList<>();

    // 构造器
    public Ride() {}
    public Ride(String rideName, int maxCapacity, Employee operator) {
        this.rideName = rideName;
        this.maxCapacity = maxCapacity;
        this.operator = operator;
    }

    // 基础属性的getter/setter
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }

    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }

    // === Part3：队列方法实现 ===
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("错误：游客信息为空，无法加入队列！");
            return;
        }
        waitingQueue.offer(visitor); // 添加到队尾
        System.out.println("成功：游客「" + visitor.getName() + "」已加入「" + rideName + "」排队队列");
    }

    @Override
    public Visitor removeVisitorFromQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("错误：「" + rideName + "」排队队列为空，无法移除游客！");
            return null;
        }
        Visitor removed = waitingQueue.poll(); // 移除队首
        System.out.println("成功：游客「" + removed.getName() + "」已离开「" + rideName + "」排队队列");
        return removed;
    }

    @Override
    public void printQueue() {
        System.out.println("=== 「" + rideName + "」排队队列（共" + waitingQueue.size() + "人）===");
        if (waitingQueue.isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        // 遍历队列打印
        int index = 1;
        for (Visitor v : waitingQueue) {
            System.out.println(index + ". " + v);
            index++;
        }
    }

    // === Part4A：历史记录方法实现 ===
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("错误：游客信息为空，无法添加到历史！");
            return;
        }
        rideHistory.add(visitor);
        System.out.println("成功：游客「" + visitor.getName() + "」已添加到「" + rideName + "」骑行历史");
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("错误：游客信息为空！");
            return false;
        }
        boolean exists = rideHistory.contains(visitor);
        if (exists) {
            System.out.println("结果：游客「" + visitor.getName() + "」在「" + rideName + "」骑行历史中");
        } else {
            System.out.println("结果：游客「" + visitor.getName() + "」不在「" + rideName + "」骑行历史中");
        }
        return exists;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("「" + rideName + "」骑行历史总游客数：" + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        System.out.println("=== 「" + rideName + "」骑行历史（共" + rideHistory.size() + "人）===");
        if (rideHistory.isEmpty()) {
            System.out.println("历史为空");
            return;
        }
        // 必须用Iterator遍历（评分要求）
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor v = iterator.next();
            System.out.println(index + ". " + v);
            index++;
        }
    }

    // === Part4B：排序方法实现 ===
    @Override
    public void sortRideHistory(Comparator<Visitor> comparator) {
        if (comparator == null) {
            System.out.println("错误：排序器为空，无法排序！");
            return;
        }
        Collections.sort(rideHistory, comparator);
        System.out.println("成功：「" + rideName + "」骑行历史已按指定规则排序");
    }

    // === Part5：骑行周期方法实现 ===
    @Override
    public void runOneCycle() {
        // 校验：无操作员 或 队列为空 → 无法运行
        if (operator == null) {
            System.out.println("错误：「" + rideName + "」无操作员，无法运行骑行周期！");
            return;
        }
        if (waitingQueue.isEmpty()) {
            System.out.println("错误：「" + rideName + "」排队队列为空，无法运行骑行周期！");
            return;
        }

        // 运行周期：从队列取maxCapacity个游客，添加到历史
        int count = 0;
        while (count < maxCapacity && !waitingQueue.isEmpty()) {
            Visitor v = removeVisitorFromQueue(); // 移除队首
            addVisitorToHistory(v);               // 添加到历史
            count++;
        }

        // 更新周期数
        numOfCycles++;
        System.out.println("成功：「" + rideName + "」第" + numOfCycles + "次骑行周期运行完成，本次载客" + count + "人");
    }

    // === Part6：导出历史到CSV ===
    @Override
    public void exportRideHistory(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("错误：文件路径为空！");
            return;
        }

        // 用BufferedWriter写入CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 写入表头
            writer.write("姓名,年龄,ID,门票号,到访日期");
            writer.newLine();

            // 写入所有历史游客
            for (Visitor v : rideHistory) {
                String line = String.join(",",
                        v.getName(),
                        String.valueOf(v.getAge()),
                        v.getId(),
                        v.getVisitorTicketId(),
                        v.getVisitDate()
                );
                writer.write(line);
                writer.newLine();
            }
            System.out.println("成功：「" + rideName + "」骑行历史已导出到：" + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("错误：文件路径不存在 → " + e.getMessage());
        } catch (IOException e) {
            System.out.println("错误：写入文件失败 → " + e.getMessage());
        }
    }

    // === Part7：从CSV导入历史 ===
    @Override
    public void importRideHistory(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("错误：文件路径为空！");
            return;
        }

        // 清空现有历史（可选，根据需求调整）
        rideHistory.clear();

        // 用BufferedReader读取CSV
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 跳过表头
            reader.readLine();

            // 逐行读取并创建游客对象
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // 校验列数是否正确
                if (parts.length != 5) {
                    System.out.println("警告：行格式错误，跳过 → " + line);
                    continue;
                }

                // 创建游客对象（处理类型转换）
                try {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    String id = parts[2];
                    String ticketId = parts[3];
                    String visitDate = parts[4];

                    Visitor v = new Visitor(name, age, id, ticketId, visitDate);
                    rideHistory.add(v);
                } catch (NumberFormatException e) {
                    System.out.println("警告：年龄格式错误，跳过 → " + line);
                }
            }
            System.out.println("成功：从「" + filePath + "」导入" + rideHistory.size() + "名游客到「" + rideName + "」骑行历史");
        } catch (FileNotFoundException e) {
            System.out.println("错误：文件不存在 → " + e.getMessage());
        } catch (IOException e) {
            System.out.println("错误：读取文件失败 → " + e.getMessage());
        }
    }
}
public class AssignmentTwo {
    public static void main(String[] args) {
        // 初始化测试数据：创建操作员、游乐设施、游客
        Employee operator = new Employee("王操作员", 30, "E12345", "EMP001", "早班08:00-16:00");
        Ride rollerCoaster = new Ride("过山车", 4, operator); // 过山车：单周期最多4人

        // 依次测试各模块
        testPart3(rollerCoaster);
        testPart4A(rollerCoaster);
        testPart4B(rollerCoaster);
        testPart5(rollerCoaster);
        testPart6(rollerCoaster);
        testPart7(rollerCoaster);
    }

    // 测试Part3：队列功能
    private static void testPart3(Ride ride) {
        System.out.println("\n==================== Part3 队列测试 ====================");
        // 创建5个游客并加入队列
        ride.addVisitorToQueue(new Visitor("张三", 20, "V001", "T1001", "2025-11-28"));
        ride.addVisitorToQueue(new Visitor("李四", 25, "V002", "T1002", "2025-11-28"));
        ride.addVisitorToQueue(new Visitor("王五", 30, "V003", "T1003", "2025-11-28"));
        ride.addVisitorToQueue(new Visitor("赵六", 35, "V004", "T1004", "2025-11-28"));
        ride.addVisitorToQueue(new Visitor("钱七", 40, "V005", "T1005", "2025-11-28"));

        // 打印队列
        ride.printQueue();

        // 移除1个游客
        ride.removeVisitorFromQueue();

        // 再次打印队列
        ride.printQueue();
    }

    // 测试Part4A：历史记录
    private static void testPart4A(Ride ride) {
        System.out.println("\n==================== Part4A 历史记录测试 ====================");
        // 添加3个游客到历史
        Visitor v1 = new Visitor("张三", 20, "V001", "T1001", "2025-11-28");
        Visitor v2 = new Visitor("李四", 25, "V002", "T1002", "2025-11-28");
        Visitor v3 = new Visitor("王五", 30, "V003", "T1003", "2025-11-28");
        ride.addVisitorToHistory(v1);
        ride.addVisitorToHistory(v2);
        ride.addVisitorToHistory(v3);

        // 检查游客是否存在
        ride.checkVisitorFromHistory(v2);
        ride.checkVisitorFromHistory(new Visitor("路人甲", 50, "V999", "T9999", "2025-11-28"));

        // 统计总数
        ride.numberOfVisitors();

        // 打印历史
        ride.printRideHistory();
    }

    // 测试Part4B：排序
    private static void testPart4B(Ride ride) {
        System.out.println("\n==================== Part4B 排序测试 ====================");
        // 添加乱序游客
        ride.addVisitorToHistory(new Visitor("孙八", 45, "V006", "T1006", "2025-11-27"));
        ride.addVisitorToHistory(new Visitor("周九", 18, "V007", "T1007", "2025-11-29"));

        // 排序前打印
        System.out.println("排序前：");
        ride.printRideHistory();

        // 按自定义规则排序
        ride.sortRideHistory(new VisitorComparator());

        // 排序后打印
        System.out.println("排序后：");
        ride.printRideHistory();
    }

    // 测试Part5：骑行周期
    private static void testPart5(Ride ride) {
        System.out.println("\n==================== Part5 骑行周期测试 ====================");
        // 清空队列，重新添加6个游客
        while (ride.removeVisitorFromQueue() != null) {} // 清空队列
        ride.addVisitorToQueue(new Visitor("游客1", 20, "V101", "T2001", "2025-11-28"));
        ride.addVisitorToQueue(new Visitor("游客2", 21, "V102", "T2002", "2025-11-28"));
        ride.addVisitorToQueue(new Visitor("游客3", 22, "V103", "T2003", "2025-11-28"));
        ride.addVisitorToQueue(new Visitor("游客4", 23, "V104", "T2004", "2025-11-28"));
        ride.addVisitorToQueue(new Visitor("游客5", 24, "V105", "T2005", "2025-11-28"));
        ride.addVisitorToQueue(new Visitor("游客6", 25, "V106", "T2006", "2025-11-28"));

        // 打印队列
        ride.printQueue();

        // 运行1次周期（最多4人）
        ride.runOneCycle();

        // 打印剩余队列和历史
        System.out.println("剩余队列：");
        ride.printQueue();
        System.out.println("骑行历史：");
        ride.printRideHistory();
    }

    // 测试Part6：导出CSV
    private static void testPart6(Ride ride) {
        System.out.println("\n==================== Part6 导出CSV测试 ====================");
        // 导出到桌面（替换为你的实际路径，Windows用\\，Mac/Linux用/）
        String exportPath = "C:\\Users\\田晏昊飞\\Desktop\\ride_history.csv"; // Windows示例
        // String exportPath = "/Users/你的用户名/Desktop/ride_history.csv"; // Mac示例
        ride.exportRideHistory(exportPath);
    }

    // 测试Part7：导入CSV
    private static void testPart7(Ride ride) {
        System.out.println("\n==================== Part7 导入CSV测试 ====================");
        // 导入刚才导出的文件（路径和Part6一致）
        String importPath = "C:\\Users\\田晏昊飞\\Desktop\\ride_history.csv"; // Windows示例
        // String importPath = "/Users/你的用户名/Desktop/ride_history.csv"; // Mac示例
        ride.importRideHistory(importPath);

        // 打印导入后的历史
        ride.printRideHistory();
    }
}
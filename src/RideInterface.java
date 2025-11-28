import java.util.Comparator;

// 定义游乐设施的所有核心方法
public interface RideInterface {
    // === Part3：队列相关 ===
    void addVisitorToQueue(Visitor visitor);   // 添加游客到排队队列
    Visitor removeVisitorFromQueue();          // 移除队首游客
    void printQueue();                         // 打印排队队列

    // === Part4A：历史记录相关 ===
    void addVisitorToHistory(Visitor visitor); // 添加游客到骑行历史
    boolean checkVisitorFromHistory(Visitor visitor); // 检查游客是否在历史中
    int numberOfVisitors();                    // 返回历史游客总数
    void printRideHistory();                   // 打印骑行历史（必须用Iterator）

    // === Part4B：排序 ===
    void sortRideHistory(Comparator<Visitor> comparator); // 排序历史记录

    // === Part5：骑行周期 ===
    void runOneCycle();                        // 运行一次骑行周期

    // === Part6/7：文件IO ===
    void exportRideHistory(String filePath);   // 导出历史到CSV
    void importRideHistory(String filePath);   // 从CSV导入历史
}
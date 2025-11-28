// 继承Person抽象类
public class Employee extends Person {
    // 2个专属变量
    private String employeeId;  // 员工号
    private String shiftTime;   // 班次（如"早班08:00-16:00"）

    // 默认构造器
    public Employee() {}

    // 带参构造器（先调用父类构造器初始化name/age/id）
    public Employee(String name, int age, String id, String employeeId, String shiftTime) {
        super(name, age, id);  // 调用父类带参构造器
        this.employeeId = employeeId;
        this.shiftTime = shiftTime;
    }

    // 专属变量的getter/setter
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getShiftTime() { return shiftTime; }
    public void setShiftTime(String shiftTime) { this.shiftTime = shiftTime; }

    // 重写toString，补充员工信息
    @Override
    public String toString() {
        return super.toString() + "，员工号：" + employeeId + "，班次：" + shiftTime;
    }
}
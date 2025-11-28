// 抽象类：不能直接实例化，仅作为子类父类
public abstract class Person {
    // 3个核心实例变量（私有，通过getter/setter访问）
    private String name;   // 姓名
    private int age;       // 年龄
    private String id;     // 唯一ID（如身份证/护照号）

    // 默认构造器（无参）
    public Person() {}

    // 带参构造器（初始化所有变量）
    public Person(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // 所有变量的getter和setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    // 可选：重写toString，方便打印人员信息
    @Override
    public String toString() {
        return "姓名：" + name + "，年龄：" + age + "，ID：" + id;
    }
}
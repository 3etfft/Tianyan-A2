// 继承Person抽象类
public class Visitor extends Person {
    // 2个专属变量
    private String visitorTicketId;  // 门票号
    private String visitDate;        // 到访日期（如"2025-11-28"）

    // 默认构造器
    public Visitor() {}

    // 带参构造器
    public Visitor(String name, int age, String id, String visitorTicketId, String visitDate) {
        super(name, age, id);
        this.visitorTicketId = visitorTicketId;
        this.visitDate = visitDate;
    }

    // 专属变量的getter/setter
    public String getVisitorTicketId() { return visitorTicketId; }
    public void setVisitorTicketId(String visitorTicketId) { this.visitorTicketId = visitorTicketId; }

    public String getVisitDate() { return visitDate; }
    public void setVisitDate(String visitDate) { this.visitDate = visitDate; }

    // 重写toString，补充游客信息
    @Override
    public String toString() {
        return super.toString() + "，门票号：" + visitorTicketId + "，到访日期：" + visitDate;
    }
}
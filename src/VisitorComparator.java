import java.util.Comparator;

// 自定义排序器：先按年龄升序，再按到访日期升序
public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 第一步：比较年龄
        int ageCompare = Integer.compare(v1.getAge(), v2.getAge());
        if (ageCompare != 0) {
            return ageCompare; // 年龄不同，直接返回结果
        }
        // 第二步：年龄相同，比较到访日期（字符串自然排序）
        return v1.getVisitDate().compareTo(v2.getVisitDate());
    }
}
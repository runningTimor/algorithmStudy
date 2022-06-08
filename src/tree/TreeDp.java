package tree;

import java.util.List;

/**
 * @author renjie
 * @description 树型DP
 * @date 2022/6/8 15:05
 */
public class TreeDp {

    public class Employee {
        public int happy;
        public List<Employee> subordinate;
    }

    public static class happyNode {
        public int lai;
        public int bulai;

        public happyNode(int lai, int bulai) {
            this.lai = lai;
            this.bulai = bulai;
        }
    }

    public static int getMaxHappy(Employee boss) {
        happyNode node = process(boss);
        return Math.max(node.bulai, node.lai);
    }

    public static happyNode process(Employee head) {
        int lai = head.happy;
        int bulai = 0;
        for (Employee employee : head.subordinate) {
            happyNode next = process(employee);
            lai += next.bulai;
            bulai = Math.max(next.bulai, next.lai);
        }
        return new happyNode(lai, bulai);
    }

    public static void main(String[] args) {

    }

}

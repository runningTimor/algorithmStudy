package xor;

/**
 * @author: renjie
 * @description: 子数组的最大异或和
 * @date: 2022/7/11 22:19
 */
public class SubArrayMaxEor {

    public static int getMaxEor(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int max = array[0];
        int sumEor = 0;
        NumberTree numberTree = new NumberTree();
        numberTree.numberTreeAdd(0);
        for (int i = 0; i < array.length; i++) {
            sumEor ^= array[i];
            max = Math.max(max, numberTree.getMax(sumEor));
            numberTree.numberTreeAdd(sumEor);
        }
        return max;
    }

    public static class Node {
        public Node[] nexts = new Node[2];
    }

    public static class NumberTree {
        public Node head = new Node();

        public void numberTreeAdd(int num) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                int path = (num >> move) & 1;
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
            }
        }

        public int getMax(int eor) {
            int res = 0;
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                int path = (eor >> move) & 1;
                int expectBest = move == 31 ? path : path ^ 1;
                int factBest = cur.nexts[expectBest] == null ? expectBest ^ 1 : expectBest;
                res |= (path ^ factBest) << move;
                cur = cur.nexts[factBest];

            }
            return res;
        }


    }


    public static void main(String[] args) {

        int[] array = {11, 1, 15, 10, 13, 4};
        System.out.println(getMaxEor(array));
    }

}

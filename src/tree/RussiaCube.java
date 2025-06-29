package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class RussiaCube {

    // 求俄罗斯方块的最大高度

    public static class SegmentTree {

        private int size;
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int N) {
            size = N << 2;
            max = new int[N << 2];
            change = new int[N << 2];
            update = new boolean[N << 2];
        }

        public void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        public void pushDown(int rt) {
            if (update[rt]) {
                update[rt << 1] = true;
                change[rt << 1] = change[rt];
                max[rt << 1] = change[rt];

                update[rt << 1 | 1] = true;
                change[rt << 1 | 1] = change[rt];
                max[rt << 1 | 1] = change[rt];
                update[rt] = false;
            }

        }

        public void update(int L, int R, int C, int left, int right, int rt) {
            if (L <= left && R >= right) {
                update[rt] = true;
                change[rt] = C;
                max[rt] = C;
                return;
            }
            pushDown(rt);
            int middle = (left + right) >> 1;
            if (L <= middle) {
                update(L, R, C, left, middle, rt << 1);
            }
            if (R >= middle + 1) {
                update(L, R, C, middle + 1, right, rt << 1 | 1);
            }

            pushUp(rt);
        }

        public int query(int L, int R, int left, int right, int rt) {
            if (L <= left && R >= right) {
                return max[rt];
            }

            pushDown(rt);
            int middle = (left + right) >> 1;
            int leftMax = 0;
            int rightMax = 0;
            if (L <= middle) {
                leftMax = query(L, R, left, middle, rt << 1);
            }
            if (R >= middle + 1) {
                rightMax = query(L, R, middle + 1, right, rt << 1 | 1);

            }
            return Math.max(leftMax, rightMax);
        }

        public static List<Integer> getMaxHeight(int[][] array) {
            TreeSet<Integer> treeSet = new TreeSet<>();
            for (int i = 0; i < array.length; i++) {
                treeSet.add(array[i][0]);
                treeSet.add(array[i][0] + array[i][1]);
            }

            int count = 0;
            HashMap<Integer, Integer> indexMap = new HashMap<>();
            for (Integer integer : treeSet) {
                indexMap.put(integer, ++count);
            }

            List<Integer> resList = new ArrayList<>();
            int max = 0;
            int N = count;
            SegmentTree segmentTree = new SegmentTree(N);
            for (int i = 0; i < array.length; i++) {
                int L = indexMap.get(array[i][0]);
                int R = indexMap.get(array[i][0] + array[i][1]);

                int query = segmentTree.query(L, R - 1, 1, N, 1);
                int currentHeight = query + array[i][1];
                max = Math.max(max, currentHeight);
                segmentTree.update(L, R - 1, currentHeight, 1, N, 1);
                resList.add(max);
            }
            return resList;
        }


    }

    public static void main(String[] args) {
        int[][] array = {{1, 2}, {3, 2}, {4, 1}};
        List<Integer> maxHeight = SegmentTree.getMaxHeight(array);
        System.out.println(maxHeight);

    }


}

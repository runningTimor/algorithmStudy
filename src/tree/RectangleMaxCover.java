package tree;

import java.util.*;

public class RectangleMaxCover {

    public static class Rectangle {
        private int up;
        private int down;
        private int left;
        private int right;

        public Rectangle(int up, int down, int left, int right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }
    }

    public static int getMaxCover(int[][] array) {
        Rectangle[] rectangles = new Rectangle[array.length];
        for (int i = 0; i < array.length; i++) {
            Rectangle rectangle = new Rectangle(array[i][0], array[i][1], array[i][2], array[i][3]);
            rectangles[i] = rectangle;
        }
        Arrays.sort(rectangles, new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                return o1.down - o2.down;
            }
        });

        int maxCover = 0;
        List<Rectangle> activeList = new ArrayList<>();
        for (int i = 0; i < rectangles.length; i++) {
            int currentDown = rectangles[i].down;
            int index = i;
            while (index < array.length && rectangles[index].down == currentDown) {
                activeList.add(rectangles[index++]);
            }
            i = index;
            activeList.sort(new Comparator<Rectangle>() {
                @Override
                public int compare(Rectangle o1, Rectangle o2) {
                    return o1.left - o2.left;
                }
            });
            activeList.removeIf(e -> e.up <= currentDown);

            PriorityQueue<Rectangle> priorityQueue = new PriorityQueue<>(new Comparator<Rectangle>() {
                @Override
                public int compare(Rectangle o1, Rectangle o2) {
                    return o1.right - o2.right;
                }
            });
            for (Rectangle rectangle : activeList) {
                int currentleft = rectangle.left;
                while (!priorityQueue.isEmpty() && priorityQueue.peek().right <= currentleft) {
                    priorityQueue.poll();
                }
                priorityQueue.add(rectangle);
                maxCover = Math.max(maxCover, priorityQueue.size());
            }
        }

        return maxCover;
    }


    public static class SegmentTree {
        private int N;
        private int[] maxArray;
        private int[] lazy;

        public SegmentTree(int size) {
            N = size + 1;
            maxArray = new int[N << 2];
            lazy = new int[N << 2];
        }

        private void pushDown(int rs) {
            if (lazy[rs] != 0) {
                maxArray[rs << 1] += lazy[rs];
                maxArray[rs << 1 | 1] += lazy[rs];
                lazy[rs << 1] += lazy[rs];
                lazy[rs << 1 | 1] += lazy[rs];
                lazy[rs] = 0;
            }
        }

        private void update(int L, int R, int C, int left, int right, int rs) {
            if (L <= left && R >= right) {
                lazy[rs] += C;
                maxArray[rs] += C;
                return;
            }

            pushDown(rs);
            int middle = (left + right) >> 1;
            if (L <= middle) {
                update(L, R, C, left, middle, rs << 1);
            }

            if (R >= middle + 1) {
                update(L, R, C, middle + 1, right, rs << 1 | 1);
            }
            maxArray[rs] = Math.max(maxArray[rs << 1], maxArray[rs << 1 | 1]);

        }

    }

    public static int getMaxCover2(int[][] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        TreeSet<Integer> treeSet = new TreeSet<>();
        List<int[]> envents = new ArrayList<>(array.length * 2);
        for (int[] a : array) {
            int[] x1 = {a[2], 1, a[1], a[0]};
            int[] x2 = {a[3], -1, a[1], a[0]};
            envents.add(x1);
            envents.add(x2);

            treeSet.add(a[0]);
            treeSet.add(a[1]);
        }
        List<Integer> integerList = new ArrayList<>(treeSet);
        integerList.sort(Integer::compareTo);

        int count = 0;
        HashMap<Integer, Integer> indexMap = new HashMap();
        for (Integer integer : integerList) {
            indexMap.put(integer, count++);
        }

        envents.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0]; // 按x坐标升序
                }
                // x坐标相同时，进入事件（1）优先于离开事件（-1）
                return o1[1] - o2[1]; // 按事件类型降序（1在前，-1在后）
            }
        });

        int ans = 0;
        SegmentTree segmentTree = new SegmentTree(count);
        for (int[] envent : envents) {

            segmentTree.update(indexMap.get(envent[2]), indexMap.get(envent[3]), envent[1], 1, segmentTree.N, 1);
            ans = Math.max(ans, segmentTree.maxArray[1]);

        }
        return ans;
    }

    public static void main(String[] args) {
        // 正常情况 (最大覆盖=2)
        int[][] rects1 = {{1, 0, 1, 3}, {2, 0, 2, 4}, {3, 0, 1, 2}};
        System.out.println(getMaxCover(rects1)); // 应输出 2
        System.out.println(getMaxCover2(rects1)); // 应输出 2

        // 边界重合 (最大覆盖=3)
        int[][] rects2 = {{2, 0, 0, 2}, {2, 0, 1, 3}, {2, 0, 1, 2}};
        System.out.println(getMaxCover(rects2)); // 应输出 3
        System.out.println(getMaxCover2(rects2)); // 应输出 3

        // 空输入
        int[][] rects3 = {};
        System.out.println(getMaxCover(rects3)); // 应输出 0
        System.out.println(getMaxCover2(rects3)); // 应输出 0
    }
}
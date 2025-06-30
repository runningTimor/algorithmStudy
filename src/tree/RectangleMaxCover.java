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

    public static void main(String[] args) {
        // 正常情况 (最大覆盖=2)
        int[][] rects1 = {{1, 0, 1, 3}, {2, 0, 2, 4}, {3, 0, 1, 2}};
        System.out.println(getMaxCover(rects1)); // 应输出 2

        // 边界重合 (最大覆盖=3)
        int[][] rects2 = {{2, 0, 0, 2}, {2, 0, 1, 3}, {2, 0, 1, 2}};
        System.out.println(getMaxCover(rects2)); // 应输出 3

        // 空输入
        int[][] rects3 = {};
        System.out.println(getMaxCover(rects3)); // 应输出 0
    }
}
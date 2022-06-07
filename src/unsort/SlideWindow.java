package unsort;

import java.util.LinkedList;

/**
 * @author renjie
 * @description 滑动窗口
 * @date 2022/6/7 21:32
 */
public class SlideWindow {

    public static class Window {
        public int L;
        public int R;
        public int[] array;
        public LinkedList<Integer> queue;

        public Window(int[] array) {
            L = -1;
            R = -1;
            this.array = array;
            this.queue = new LinkedList();
        }

        //从双端队列右侧(尾部)添加元素
        public void addQueueFromRight(int element) {
            while (!queue.isEmpty() && array[queue.peekLast()] <= array[element]) {
                queue.pollLast();
            }
            queue.addLast(element);
            R++;
        }

        //双端队列头部值过期
        public void removequeueFirst() {
            if (queue.isEmpty() || L == R) {
                return;
            }
            queue.pollFirst();
            L++;
        }

        public int[] getMaxWindow(int windowSize) {
            int length = array.length - windowSize + 1;
            if (array == null || array.length == 0 || windowSize < 1 || array.length < windowSize) {
                return null;
            }
            int[] res = new int[length];
            int index = 0;
            int i = 0;
            while (index < windowSize - 1) {
                addQueueFromRight(index++);
            }
            for (; index < array.length; index++) {
                addQueueFromRight(index);
                if (queue.peekFirst() <= index - windowSize) {
                    removequeueFirst();
                }
                res[i++] = array[queue.peekFirst()];
            }
            return res;
        }

    }

    public static void main(String[] args) {
        int[] array = {4, 3, 5, 4, 3, 3, 6, 7};
        Window window = new Window(array);
        window.getMaxWindow(3);
        System.out.print("");
    }

}

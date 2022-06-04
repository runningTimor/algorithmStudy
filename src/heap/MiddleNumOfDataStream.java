package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author renjie
 * @description 获取数据流中位数
 * @date 2022/6/4 20:19
 */
public class MiddleNumOfDataStream {

    public static class ComparatorNum implements Comparator<Integer> {

        @Override public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static int getMiddleNum(int[] array) {
        PriorityQueue<Integer> smallQueue = new PriorityQueue<>();
        PriorityQueue<Integer> bigQueue = new PriorityQueue<>(new ComparatorNum());
        bigQueue.add(array[0]);
        int bigSize = 1;
        int smallSize = 0;
        for (int index = 1; index < array.length; index++) {

            if (array[index] > bigQueue.peek()) {
                smallQueue.add(array[index]);
                smallSize++;
                if (smallSize - bigSize > 1) {
                    bigQueue.add(smallQueue.poll());
                    bigSize++;
                    smallSize--;
                }
            } else {
                bigQueue.add(array[index]);
                bigSize++;
                if (bigSize - smallSize > 1) {
                    smallQueue.add(bigQueue.poll());
                    smallSize++;
                    bigSize--;
                }
            }
        }
        return bigSize > smallSize ? bigQueue.peek() : smallQueue.peek();
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 6, 5, 4, 7};
        System.out.println(getMiddleNum(array));
    }

}

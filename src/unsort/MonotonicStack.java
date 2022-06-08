package unsort;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author renjie
 * @description 单调栈
 * @date 2022/6/8 11:13
 */
public class MonotonicStack {

    public static class MinNode {
        public Integer leftMin;
        public Integer rightMin;

        public MinNode(Integer leftMin, Integer rightMin) {
            this.leftMin = leftMin;
            this.rightMin = rightMin;
        }
    }

    //array数组中每个数，左右最近且小于当前位置的数
    public static MinNode[] getNearestMin(int[] array) {
        //栈底到栈顶的数字从小到大
        Stack<LinkedList<Integer>> monotonicStack = new Stack<>();
        MinNode[] res = new MinNode[array.length];
        for (int i = 0; i < array.length; i++) {
            //生成栈内的节点信息
            while (!monotonicStack.isEmpty() && array[i] < array[monotonicStack.peek().getFirst()]) {
                LinkedList<Integer> linkList = monotonicStack.pop();
                Integer leftMin = monotonicStack.isEmpty() ? null : monotonicStack.peek().getLast();
                Integer rightMin = i;
                while (!linkList.isEmpty()) {
                    MinNode node = new MinNode(leftMin, rightMin);
                    res[linkList.pollLast()] = node;
                }
            }
            if (monotonicStack.isEmpty() || array[i] > array[monotonicStack.peek().getFirst()]) {
                LinkedList linkList = new LinkedList();
                linkList.addLast(i);
                monotonicStack.push(linkList);
            }
            if (array[i] == array[monotonicStack.peek().getFirst()]) {
                monotonicStack.peek().addLast(i);
            }
        }
        //处理栈中剩余的元素
        while (!monotonicStack.isEmpty()) {
            LinkedList<Integer> linkList = monotonicStack.pop();
            Integer rightMin = null;
            Integer leftMin = monotonicStack.isEmpty() ? null : monotonicStack.peek().getLast();
            while (!linkList.isEmpty()) {
                MinNode node = new MinNode(leftMin, rightMin);
                res[linkList.pollLast()] = node;
            }
        }
        return res;

    }

    //前缀和
    public static int[] getPreSum(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        int[] res = new int[array.length];
        res[0] = array[0];
        for (int index = 1; index < array.length; index++) {
            res[index] = res[index - 1] + array[index];
        }
        return res;
    }

    public static int getMaxA(int[] array) {
        MinNode[] minNodes = getNearestMin(array);
        int[] preSum = getPreSum(array);
        int max = Integer.MIN_VALUE;
        for (int index = 0; index < array.length; index++) {
            MinNode node = minNodes[index];
            int sum = 0;
            //将左右的小值索引为空的处理，方便计算前缀和
            int L = node.leftMin == null ? 0 : node.leftMin;
            int R = node.rightMin == null ? array.length - 1 : node.rightMin;
            if (node.leftMin == null) {
                sum = node.rightMin == null ? preSum[R] : preSum[R - 1];
            } else {
                sum = node.rightMin == null ? preSum[R] - preSum[L] : preSum[R - 1] - preSum[L];
            }
            max = Math.max(sum*array[index], max);

        }
        return max;

    }

    public static void main(String[] args) {
        int[] array = {4, 3, 5, 4, 3, 3, 6, 7};
        System.out.print(getMaxA(array));
    }

}


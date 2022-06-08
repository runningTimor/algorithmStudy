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
            if (monotonicStack.isEmpty() || array[i] > array[monotonicStack.peek().getFirst()]) {
                LinkedList linkList = new LinkedList();
                linkList.addLast(i);
                monotonicStack.push(linkList);
            } else if (array[i] == array[monotonicStack.peek().getFirst()]) {
                LinkedList linkList = monotonicStack.peek();
                linkList.addLast(i);
            } else {
                //生成栈内的节点信息
                while (!monotonicStack.isEmpty() && array[i] < array[monotonicStack.peek().getFirst()]) {
                    LinkedList<Integer> linkList = monotonicStack.pop();
                    Integer leftMin = null;
                    if (!monotonicStack.isEmpty()) {
                        leftMin = monotonicStack.peek().getLast();
                    }
                    Integer rightMin = i;
                    while (!linkList.isEmpty()) {
                        MinNode node = new MinNode(leftMin, rightMin);
                        res[linkList.pollLast()] = node;
                    }
                }
                //将i压入栈
                i--;

            }
        }
        //处理栈中剩余的元素
        while (!monotonicStack.isEmpty()) {
            LinkedList<Integer> linkList = monotonicStack.pop();
            Integer rightMin = null;
            Integer leftMin = null;
            if (!monotonicStack.isEmpty()) {
                leftMin = monotonicStack.peek().getLast();
            }
            while (!linkList.isEmpty()) {
                MinNode node = new MinNode(leftMin, rightMin);
                res[linkList.pollLast()] = node;
            }
        }
        return res;

    }

    public static void main(String[] args) {
        int[] array = {4,3,5,4,3,3,6,7};
        getNearestMin(array);

    }

}


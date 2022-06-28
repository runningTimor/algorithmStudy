package unsort;

import java.util.Stack;

/**
 * @author: renjie
 * @description: 使用栈结构实现队列结构
 * @date: 2022/6/28 11:30
 */
public class StackToQueue {

    private Stack<Integer> popStack;
    private Stack<Integer> pushStack;

    public void push(int a) {
        dao();
        pushStack.push(a);
    }

    public Integer pop() {
        dao();
        return popStack.pop();
    }

    public void dao() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

}

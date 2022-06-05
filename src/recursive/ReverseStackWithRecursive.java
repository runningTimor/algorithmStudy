package recursive;

import java.util.Stack;

/**
 * @author renjie
 * @description 不使用额外空间，仅使用递归将栈逆序
 * @date 2022/6/5 20:39
 */
public class ReverseStackWithRecursive {

    public static int getStackDown(Stack<Integer> stack) {
        int res = stack.pop();
        if (stack.isEmpty()) {
            return res;
        } else {
            int last = getStackDown(stack);
            stack.push(res);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int last = getStackDown(stack);
        reverse(stack);
        stack.push(last);
    }

    public static void main(String[] args) {

    }

}

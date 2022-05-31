package tree;

import java.util.Stack;

// 非递归方式遍历二叉树
public class NotRecursive {

    public static class TreeNode {
        public TreeNode leftChild;
        public TreeNode rightChild;
        public int value;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    // 前序遍历
    public static void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.println(current.value);
            if (current.rightChild != null) {
                stack.push(current.rightChild);
            }
            if (current.leftChild != null) {
                stack.push(current.leftChild);
            }
        }
    }

    // 后序遍历
    public static void lastOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            TreeNode current = stack1.pop();
            stack2.push(current);
            if (current.leftChild != null) {
                stack1.push(current.leftChild);
            }
            if (current.rightChild != null) {
                stack1.push(current.rightChild);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().value);
        }
    }

    //中序遍历
    public static void inOrder(TreeNode head){
        if (head==null){
            return;
        }
        //如果有右树，需要将右树的左边界全部入栈
        TreeNode current = head;
        Stack<TreeNode> stack = new Stack<>();
        while (current!=null){
            stack.push(current);
            current = current.leftChild;
        }
        
        while (!stack.isEmpty()){
            current = stack.pop();
            System.out.println(current.value);
            if (current.rightChild!=null){
                current = current.rightChild;
                while (current != null) {
                    stack.push(current);
                    current = current.leftChild;
                }
            }
        }

    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.leftChild=node2;
        node1.rightChild=node3;

        node2.leftChild=node4;
        node2.rightChild=node5;

        node3.leftChild=node6;
        node3.rightChild=node7;

//        preOrder(node1);
//        inOrder(node1);
        lastOrder(node1);
    }

}

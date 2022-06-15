package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 非递归方式遍历二叉树
public class NotRecursive {

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

    // 中序遍历
    public static void inOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        // 首先将整颗树的左边界全部入栈
        TreeNode current = head;
        Stack<TreeNode> stack = new Stack<>();
        while (current != null) {
            stack.push(current);
            current = current.leftChild;
        }

        while (!stack.isEmpty()) {
            current = stack.pop();
            System.out.println(current.value);
            if (current.rightChild != null) {
                current = current.rightChild;
                while (current != null) {
                    stack.push(current);
                    current = current.leftChild;
                }
            }
        }
    }

    // 二叉树宽度优先遍历，求二叉树最大宽度
    public static int getMaxBroad1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        HashMap<TreeNode, Integer> nodeLevelMap = new HashMap<>();
        int currentLevelNode = 0;
        int maxBroad = Integer.MIN_VALUE;
        int currentLevel = 1;
        nodeLevelMap.put(head, 1);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (currentLevel == nodeLevelMap.get(current)) {
                currentLevelNode++;
            } else {
                currentLevel += 1;
                maxBroad = Math.max(maxBroad, currentLevelNode);
                currentLevelNode = 1;
            }
            // System.out.println(current.value);
            if (current.leftChild != null) {
                queue.add(current.leftChild);
                nodeLevelMap.put(current.leftChild, currentLevel + 1);
            }
            if (current.rightChild != null) {
                queue.add(current.rightChild);
                nodeLevelMap.put(current.rightChild, currentLevel + 1);
            }
        }
        maxBroad = Math.max(maxBroad, currentLevelNode);
        return maxBroad;
    }

    // 不用hashmap方式求解二叉树最大宽度
    public static int getMaxBroad2(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int maxBroad = Integer.MIN_VALUE;
        TreeNode currentLevelEndNode = head;
        TreeNode nextLevelEndNode = null;
        int currentLevelNodes = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (currentNode.leftChild != null) {
                queue.add(currentNode.leftChild);
                nextLevelEndNode = currentNode.leftChild;
            }
            if (currentNode.rightChild != null) {
                queue.add(currentNode.rightChild);
                nextLevelEndNode = currentNode.rightChild;
            }
            currentLevelNodes++;
            // 当前节点是本层最后一个节点
            if (currentLevelEndNode == currentNode) {
                currentLevelEndNode = nextLevelEndNode;
                nextLevelEndNode = null;
                maxBroad = Math.max(maxBroad, currentLevelNodes);
                currentLevelNodes = 0;
            }
        }
        return maxBroad;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);

        node1.leftChild = node2;
        node1.rightChild = node3;

        node2.leftChild = node4;
        node2.rightChild = node5;

        node3.leftChild = node6;

        node5.leftChild = node7;
        node5.rightChild = node8;

        node6.leftChild = node9;
        node6.rightChild = node10;

        // preOrder(node1);
        // inOrder(node1);
        // lastOrder(node1);
        System.out.println(getMaxBroad1(node1));
        System.out.println(getMaxBroad2(node1));
    }

}

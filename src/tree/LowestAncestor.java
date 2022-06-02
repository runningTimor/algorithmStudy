package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description 两个二叉树节点的最低公共祖先
 * @auther renjie
 * @date 2022/6/2 18:38
 */
public class LowestAncestor {

    public static class Node {
        private Node right;
        private Node left;
        private Node parent;
        private String value;

        public Node(String value) {
            this.value = value;
        }
    }

    public static Node getLowestAncestor(Node head, Node node1, Node node2) {
        if (head == null || head == node1 || head == node2) {
            return head;
        }
        Node left = getLowestAncestor(head.left, node1, node2);
        Node right = getLowestAncestor(head.right, node1, node2);
        if (left != null && right != null) {
            return head;
        }
        return left == null ? right : left;
    }

    public static Node getSuccessorNode(Node current) {
        if (current == null) {
            return null;
        }
        // 当前节点有右子树，则后继节点为右子树最左节点
        if (current.right != null) {
            Node right = current.right;
            while (right.left != null) {
                right = right.left;
            }
            return right;
        }
        // 当前节点无右子树，则向上查找到自己为父节点的左孩子时，返回父节点
        Node parrent = current.parent;
        while (parrent != null && parrent.right == current) {
            current = parrent;
            parrent = current.parent;
        }
        return parrent;
    }

    // 二叉树序列化
    public static String treeSerial(Node head) {
        if (head == null) {
            return "#";
        }
        String left = treeSerial(head.left);
        String right = treeSerial(head.right);
        return head.value + "_" + left + "_" + right;
    }

    public static Node treeDeserial(String treeStr) {
        if (treeStr==null||treeStr.length()==0){
            return null;
        }
        String[] chars = treeStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for(String c :chars){
            queue.add(c);
        }
        return process(queue);

    }

    public static Node process(Queue<String> queue){
        String s = queue.poll();
        if ("#".equals(s)) {
            return null;
        }
        Node parrent = new Node(s);
        Node leftChild = process(queue);
        Node rightChild = process(queue);
        parrent.left = leftChild;
        parrent.right = rightChild;
        return parrent;

    }

    public static void main(String[] args) {
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        node1.left = node2;
        node1.right = node3;
        Node res = treeDeserial(treeSerial(node1));
        System.out.println(treeSerial(node1));


    }

}

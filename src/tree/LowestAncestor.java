package tree;

/**
 * @description 两个二叉树节点的最低公共祖先
 * @auther renjie
 * @date 2022/6/2 18:38
 */
public class LowestAncestor {

    public class Node {
        private Node right;
        private Node left;
        private int value;
    }

    public static Node getLowestAncestor(Node head, Node node1, Node node2) {
        if (head == null || head == node1 || head == node2) {
            return head;
        }
        Node left = getLowestAncestor(head.left,node1,node2);
        Node right = getLowestAncestor(head.right,node1,node2);
        if (left!=null&&right!=null){
            return head;
        }
        return left==null?right:left;

    }

    public static void main(String[] args) {

    }

}

package unsort;

/**
 * 二叉树转换成双向链表
 */
public class BinaryTreeToLinkList {

    /**
     * 二叉树节点
     */
    public static class BinaryTreeNode{
        private BinaryTreeNode left;
        private BinaryTreeNode right;

        public BinaryTreeNode() {

        }
    }

    /**
     * 递归返回数据，链表的头尾节点
     */
    public static class Info {
        private BinaryTreeNode head;
        private BinaryTreeNode tail;

        public Info(BinaryTreeNode head, BinaryTreeNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    /**
     * 将二叉树转换成链表
     * @param current
     * @return
     */
    public static Info convert(BinaryTreeNode current){
        if (current==null){
            return new Info(null,null);
        }
        Info leftInfo = convert(current.left);
        Info rightInfo = convert(current.right);
        BinaryTreeNode leftTail = leftInfo.tail;
        BinaryTreeNode rightHead = rightInfo.head;

        if (leftTail!=null){
            leftTail.right = current;
        }
        current.left = leftTail;

        if (rightHead!=null){
            rightHead.left = current;
        }
        current.right = rightHead;

        return new Info(leftInfo.head==null?current:leftInfo.head,
                rightInfo.tail==null?current:rightInfo.tail);

    }

    public static void main(String[] args) {

    }




}

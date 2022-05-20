/**
 * 求完全二叉树节点个数
 */
public class CompleteBinaryTreeNode {

    public static class TreeNode {
        private int value;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    //统计出完全二叉树的高度
    public static int treeHigh(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int high = 0;
        while (head != null) {
            head = head.leftChild;
            high++;
        }
        return high;
    }

    public static int countTreeNode(TreeNode head, int high) {
        if (head == null) {
            return 0;
        }
        int count = 0;
        int rightChildHigh = treeHigh(head.rightChild);
        //右树最左节点深度等于树高度-1，说明左子树为满二叉树
        if (rightChildHigh == high - 1) {
            count += 1<<(high - 1);
            //递归统计右子树的节点数
            count += countTreeNode(head.rightChild, high - 1);
        } else {
            //右树最左节点深度等于树高度-2，说明右子树为满二叉树
            count += 1<<(high - 2);
            count += countTreeNode(head.leftChild, high - 1);
        }
        return count;

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
        TreeNode node11= new TreeNode(11);
        TreeNode node12 = new TreeNode(12);
        node1.leftChild = node2;
        node1.rightChild = node3;
        node2.leftChild = node4;
        node2.rightChild = node5;
        node3.leftChild = node6;
        node3.rightChild = node7;
        node4.leftChild = node8;
        node4.rightChild = node9;
        node5.leftChild = node10;
        node5.rightChild = node11;
//        node6.leftChild = node12;
        int high = treeHigh(node1);
        System.out.println(countTreeNode(node1,high));

    }
}

package tree;

/**
 * @author renjie
 * @description 二叉树Morris遍历
 * @date 2022/6/8 16:56
 */
public class Morris {

    //前序遍历，第一次访问时处理
    public static void preProcess(TreeNode current) {
        while (current != null) {
            TreeNode mostRight = current.leftChild;
            //左子树不为空
            if (mostRight != null) {
                //找到左子树最右节点
                while (mostRight.rightChild != null && mostRight.rightChild != current) {
                    mostRight = mostRight.rightChild;
                }
                if (mostRight.rightChild == null) {
                    //将左子树最右节点的right指针指向current
                    mostRight.rightChild = current;
                    System.out.print(current.value);
                    current = current.leftChild;
                } else {
                    //左子树最右节点的right指针如果指向current，则将他指回空
                    mostRight.rightChild = null;
                    current = current.rightChild;
                }
            } else {
                //左子树为空直接向右移动
                System.out.print(current.value);
                current = current.rightChild;
            }
        }
    }

    //中序遍历，第二次访问时处理，只访问一次的节点直接处理
    public static void inProcess(TreeNode current) {
        while (current != null) {
            TreeNode mostRight = current.leftChild;
            //左子树不为空
            if (mostRight != null) {
                //找到左子树最右节点
                while (mostRight.rightChild != null && mostRight.rightChild != current) {
                    mostRight = mostRight.rightChild;
                }
                if (mostRight.rightChild == null) {
                    //将左子树最右节点的right指针指向current
                    mostRight.rightChild = current;
                    current = current.leftChild;
                } else {
                    //左子树最右节点的right指针如果指向current，则将他指回空
                    mostRight.rightChild = null;
                    System.out.print(current.value);
                    current = current.rightChild;
                }
            } else {
                //左子树为空直接向右移动
                System.out.print(current.value);
                current = current.rightChild;
            }
        }
    }

    //后序遍历，仅第二次访问时处理，逆序打印左数的右边界
    public static void lastProcess(TreeNode head) {
        TreeNode current = head;
        while (current != null) {
            TreeNode mostRight = current.leftChild;
            //左子树不为空
            if (mostRight != null) {
                //找到左子树最右节点
                while (mostRight.rightChild != null && mostRight.rightChild != current) {
                    mostRight = mostRight.rightChild;
                }
                if (mostRight.rightChild == null) {
                    //将左子树最右节点的right指针指向current
                    mostRight.rightChild = current;
                    current = current.leftChild;
                } else {
                    //左子树最右节点的right指针如果指向current，则将他指回空
                    mostRight.rightChild = null;
                    //逆序打印左树右边界
                    printEdge(current.leftChild);
                    current = current.rightChild;
                }
            } else {
                //左子树为空直接向右移动
                current = current.rightChild;
            }
        }
        //最后逆序打印整棵树的右边界
        printEdge(head);
    }

    public static void printEdge(TreeNode head) {
        TreeNode newHead = reverse(head);
        while (newHead != null) {
            System.out.print(newHead.value);
            newHead = newHead.rightChild;
        }
        reverse(newHead);

    }

    //右边界的逆序，返回新的头节点
    public static TreeNode reverse(TreeNode head) {
        TreeNode pre = null;
        TreeNode next = null;
        while (head != null) {
            next = head.rightChild;
            head.rightChild = pre;
            pre = head;
            head = next;
        }
        return pre;
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
        //        preProcess(node1);
        //        inProcess(node1);
        lastProcess(node1);

    }

}

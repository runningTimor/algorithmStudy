package tree;

/**
 * 最大二叉搜索子树
 */
public class MaxBST {

    public class Node {
        private Node right;
        private Node left;
        private int value;
    }

    public class Info {
        private Node maxBSTHead;
        private int max;
        private int min;
        private int size;

        public Info(Node maxBSTHead, int max, int min, int size) {
            this.maxBSTHead = maxBSTHead;
            this.max = max;
            this.min = min;
            this.size = size;
        }
    }

    public Info getMaxBST(Node current) {
        if (current == null) {
            return null;
        }
        Info left = getMaxBST(current.left);
        Info right = getMaxBST(current.right);

        Node maxBSTHead = null;
        int max = current.value;
        int min = current.value;
        int maxBSTsize = 0;
        if (left != null) {
            max = Math.max(left.max, max);
            min = Math.min(left.min, min);

        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
        }
        if (left != null) {
            maxBSTHead = left.maxBSTHead;
            maxBSTsize = left.size;
        }
        if (right != null && right.size > maxBSTsize) {
            maxBSTHead = right.maxBSTHead;
            maxBSTsize = right.size;
        }
        if ((left == null || left.maxBSTHead == current.left) &&
                (right == null || right.maxBSTHead == current.right)
        ) {
            if (left.max < current.value && right.min > current.value) {
                maxBSTHead = current;
                maxBSTsize = (right == null ? 0 : right.size) + (left == null ? 0 : left.size) + 1;
            }
        }

        return new Info(maxBSTHead, max, min, maxBSTsize);
    }

    public static void main(String[] args) {

    }

}

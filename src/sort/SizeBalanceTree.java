package sort;

public class SizeBalanceTree<K extends Comparable<K>, V> {
    public TreeNode<K, V> head;

    class TreeNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public int size;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }


    private TreeNode maintain(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        int leftSize = leftNode == null ? 0 : leftNode.size;
        int rightSize = rightNode == null ? 0 : rightNode.size;

        if (leftNode != null && leftNode.left != null && leftNode.left.size > rightSize) {
            node = rightRoate(node);
            node.right = maintain(node.right);
            node = maintain(node);
        } else if (leftNode != null && leftNode.right != null && leftNode.right.size > rightSize) {
            node.left = leftRoate(node.left);
            node = rightRoate(node);
            node.left = maintain(node.left);
            node.right = maintain(node.right);
            node = maintain(node);
        } else if (rightNode != null && rightNode.right != null && rightNode.right.size > leftSize) {
            node = leftRoate(node);
            node.left = maintain(node.left);
            node = maintain(node);
        } else if (rightNode != null && rightNode.left != null && rightNode.left.size > leftSize) {
            node.right = rightRoate(node.right);
            node = leftRoate(node);
            node.left = maintain(node.left);
            node.right = maintain(node.right);
            node = maintain(node);
        }
        return node;
    }

    private TreeNode leftRoate(TreeNode node) {
        TreeNode rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        rightNode.size = node.size;
        node.size = 1 + (node.left != null ? node.left.size : 0) + (node.right != null ? node.right.size : 0);
        return rightNode;
    }

    private TreeNode rightRoate(TreeNode node) {
        TreeNode leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        leftNode.size = node.size;
        node.size = 1 + (node.left != null ? node.left.size : 0) + (node.right != null ? node.right.size : 0);
        return leftNode;
    }

    private TreeNode<K, V> findLastNode(K key) {
        TreeNode<K, V> current = head;
        TreeNode pre = null;
        while (current != null) {
            if (key.compareTo(current.key) == 0) {
                return current;
            } else if (key.compareTo(current.key) < 0) {
                pre = current;
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                pre = current;
                current = current.right;
            }
        }
        return pre;
    }

    public void put(K key, V value) {
        TreeNode<K, V> node = findLastNode(key);
        if (node != null && node.key.compareTo(key) == 0) {
            node.value = value;
            return;
        }

        head = add(head, key, value);
    }

    private TreeNode add(TreeNode<K, V> current, K key, V value) {
        if (current == null) {
            return new TreeNode(key, value);
        }
        current.size++;
        if (key.compareTo(current.key) > 0) {
            current.right = add(current.right, key, value);
        } else if (key.compareTo(current.key) < 0) {
            current.left = add(current.left, key, value);
        }
        return maintain(current);
    }

    public void remove(K key) {
        TreeNode<K, V> node = findLastNode(key);
        if (node == null || node.key.compareTo(key) != 0) {
            return;
        }
        head = delete(head, key);
    }

    private TreeNode delete(TreeNode<K, V> current, K key) {
        current.size--;
        if (key.compareTo(current.key) > 0) {
            current.right = delete(current.right, key);
        } else if (key.compareTo(current.key) < 0) {
            current.left = delete(current.left, key);
        } else {
            if (current.left == null && current.right == null) {
                current = null;
            } else if (current.left != null && current.right == null) {
                current = current.left;
            } else if (current.left == null && current.right != null) {
                current = current.right;
            } else {
                TreeNode rightNode = current.right;
                TreeNode mostLeft = rightNode;
                TreeNode pre = null;
                while (mostLeft.left != null) {
                    mostLeft.size--;
                    pre = mostLeft;
                    mostLeft = mostLeft.left;
                }
                mostLeft.size = current.size;
                mostLeft.left = current.left;
                if (mostLeft != rightNode) {
                    pre.left = mostLeft.right;
                    mostLeft.right = rightNode;
                }
                current = mostLeft;
            }
        }
        return maintain(current);
    }


}

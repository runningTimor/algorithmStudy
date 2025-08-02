package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳表
 */
public class SkipList {

    public static class SkipNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        List<SkipNode<K, V>> nexts;

        public SkipNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.nexts = new ArrayList<>();
        }

        /**
         * 头节点存null
         *
         * @param key
         * @return
         */
        public boolean isKeyLess(K key) {
            if (this.key == null) {
                return true;
            }
            if (key == null) {
                return false;
            }
            return this.key.compareTo(key) < 0;
        }

        public boolean isEquals(K key) {
            if (this.key == null && key == null) {
                return true;
            }
            return this.key != null && key != null && this.key.compareTo(key) == 0;

        }

    }

    public static class SkipListMap<K extends Comparable<K>, V>{
        private static final double constants = 0.5;
        private SkipNode head ;
        int maxLevel ;
        int size ;

        public SkipListMap() {
            this.head = new SkipNode(null, null);
            head.nexts.add(null);
            this.maxLevel = 0;
            this.size = 0;
        }

        /**
         * 在当前层中找到小于key的最右节点
         *
         * @param key
         * @param current
         * @param level
         * @return
         */
        public SkipNode getMostRightLessInLevel(K key, SkipNode current, int level) {

            if (key == null) {
                return null;
            }

            SkipNode pre = current;
            while (current != null) {
                if (!current.isKeyLess(key)) {
                    break;
                }
                pre = current;
                current = (SkipNode) current.nexts.get(level);
            }
            return pre;
        }

        /**
         * 在第0层找到小于key左右的节点
         *
         * @param key
         * @param head
         * @param maxLevel
         * @return
         */
        public SkipNode getMostRightLessInTree(K key, SkipNode head, int maxLevel) {

            SkipNode current = head;
            int level = maxLevel;
            while (level >= 0) {
                current = getMostRightLessInLevel(key, current, level--);
            }

            return current;
        }

        public boolean contains(K key) {

            if (key == null) {
                return false;
            }
            SkipNode skipNode = getMostRightLessInTree(key, head, maxLevel);
            SkipNode next = (SkipNode) skipNode.nexts.get(0);
            if (next != null && next.isEquals(key)) {
                return true;
            }
            return false;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            SkipNode skipNode = getMostRightLessInTree(key, head, maxLevel);
            SkipNode next = (SkipNode) skipNode.nexts.get(0);
            if (next != null && next.isEquals(key)) {
                next.value = value;
                return;
            }

            SkipNode<K, V> newNode = new SkipNode<>(key, value);
            newNode.nexts.add(null);

            int newNodeLevel = 0;
            size++;
            while (Math.random() > constants) {
                newNodeLevel++;
                newNode.nexts.add(null);
            }

            while (newNodeLevel > maxLevel) {
                maxLevel++;
                head.nexts.add(null);
            }

            int level = maxLevel;
            SkipNode pre = head;
            while (level >= 0) {

                pre = getMostRightLessInLevel(key, pre, level);
                if (newNodeLevel >= level) {
                    newNode.nexts.set(level, (SkipNode) pre.nexts.get(level));
                    pre.nexts.set(level, newNode);

                }
                level--;
            }
        }

        public void remove(K key) {
            if (!contains(key)){
                return;
            }
            size--;
            int level = maxLevel;
            SkipNode pre = head;
            while (level >= 0) {

                pre = getMostRightLessInLevel(key, pre, level);
                SkipNode next = (SkipNode) pre.nexts.get(level);
                if (next != null && next.isEquals(key)) {
                    pre.nexts.set(level, next.nexts.get(level));
                }
                if (level != 0 && pre == head && next.nexts.get(level) == null) {
                    maxLevel--;
                    head.nexts.remove(level);
                }
                level--;
            }

        }

    }

}

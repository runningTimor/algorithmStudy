package unsort;

import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

/**
 * @description 并查集
 * @auther renjie
 * @date 2022/6/3 17:56
 */
public class UnionFind {

    public static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }
    }

    public static class UnionFindSet<V> {
        public HashMap<V, Element> nodeMap;
        public HashMap<Element, Element> fatherMap;
        public HashMap<Element, Integer> sizeMap;

        public UnionFindSet(Collection<V> list) {
            nodeMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Element element = new Element(value);
                nodeMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        public Element findHead(V v) {
            Element element = nodeMap.get(v);
            Stack<Element> stack = new Stack<>();
            while (element != fatherMap.get(element)) {
                stack.push(element);
                element = fatherMap.get(element);
            }
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), element);
            }
            return element;
        }

        public boolean isSameSet(V v1, V v2) {
            if (!nodeMap.containsKey(v1) || !nodeMap.containsKey(v2)) {
                return false;
            }
            if (findHead(v1) == findHead(v2)) {
                return true;
            }
            return false;
        }

        public void union(V v1, V v2) {
            if (!nodeMap.containsKey(v1) || !nodeMap.containsKey(v2)) {
                return;
            }
            Element head1 = findHead(v1);
            Element head2 = findHead(v2);
            if (head1 == head2) {
                return;
            }
            int size1 = sizeMap.get(head1);
            int size2 = sizeMap.get(head2);
            Element big = size1 >= size2 ? head1 : head2;
            Element small = size1 < size1 ? head1 : head2;
            fatherMap.put(small, big);
            sizeMap.put(big, sizeMap.get(big) + sizeMap.get(small));
            sizeMap.remove(small);
        }

    }

}

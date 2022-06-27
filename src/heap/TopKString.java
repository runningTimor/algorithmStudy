package heap;

import java.util.HashMap;

/**
 * @author: renjie
 * @description: 动态添加字符串，返回词频为top K的字符串
 * @date: 2022/6/27 20:27
 */
public class TopKString {

    public static class SmallHeap {
        private HashMap<String, Integer> countMap;
        private String[] heap;
        private int heapSize;
        private HashMap<String, Integer> indexMap;

        public SmallHeap(int size) {
            this.countMap = new HashMap<>();
            this.heap = new String[size];
            this.indexMap = new HashMap<>();
            heapSize = 0;
        }

        public void add(String s) {
            if (!countMap.containsKey(s)) {
                countMap.put(s, 1);
            } else {
                countMap.put(s, countMap.get(s) + 1);
            }
            if (indexMap.containsKey(s) && indexMap.get(s) != -1) {
                heapfiy(indexMap.get(s));
            }else {
                if (heapSize < heap.length) {
                    heapInsert(s);
                } else if (countMap.get(heap[0]) < countMap.get(s)) {
                    pop();
                    heapInsert(s);
                }
            }


        }

        public void heapInsert(String s) {
            indexMap.put(s, heapSize);
            heap[heapSize++] = s;
            int index = heapSize - 1;
            while (countMap.get(heap[index]) < countMap.get(heap[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapfiy(int index) {
            int leftChild = 2 * index + 1;
            while (leftChild < heapSize) {
                int rightChild = leftChild + 1;
                int smallChild = rightChild < heapSize &&
                        countMap.get(heap[rightChild]) < countMap.get(heap[leftChild]) ?
                        rightChild : leftChild;
                if (countMap.get(heap[index]) < countMap.get(heap[smallChild])) {
                    break;
                }
                swap(index, smallChild);
                index = smallChild;
                leftChild = 2 * index + 1;
            }
        }

        public String pop() {
            String res = heap[0];
            swap(0, --heapSize);
            heapfiy(0);
            indexMap.put(res, -1);
            return res;
        }

        public void swap(int i, int j) {
            String temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
            indexMap.put(heap[i], i);
            indexMap.put(heap[j], j);
        }

    }

    public static void main(String[] args) {
        String[] strings = {"a", "a", "c", "d", "e", "e", "b", "b"};
        int k = 3;
        SmallHeap heap = new SmallHeap(k);
        for (String s : strings) {
            heap.add(s);

        }
    }
}

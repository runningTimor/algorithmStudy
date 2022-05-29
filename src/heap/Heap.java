package heap;

import java.util.Arrays;

//用数组实现堆结构
public class Heap {

    //向大根堆中添加一个元素
    public static void heapInsert(int[] array, int curIndex) {
        //调整结构满足大根堆
        while (array[curIndex] > array[(curIndex - 1) / 2]) {
            swap(array, curIndex, (curIndex - 1) / 2);
            curIndex = (curIndex - 1) / 2;
        }
    }

    //从index开始向下调整堆结构
    public static void heapPop(int[] array, int index, int heapSize) {
        int leftChild = 2 * index + 1;
        while (leftChild < heapSize) {

            int rightChild = leftChild + 1;
            int largest = (rightChild < heapSize && array[rightChild] > array[leftChild]) ? rightChild : leftChild;
            if (array[index] >= array[largest]) {
                break;
            }
            swap(array, index, largest);
            index = largest;
            leftChild = 2 * index + 1;
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    //利用大根堆进行排序
    public static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            heapInsert(array, i);
        }
        //弹出大根堆堆顶的数字，与数组末尾数字交换，并从新调整大根堆
        int heapSize = array.length;
        while (heapSize > 0) {
            swap(array, 0, --heapSize);
            heapPop(array, 0, heapSize);
        }
    }


    public static void main(String[] args) {
        int[] heap = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        heapSort(heap);


        int heapSize = 0;
        for (int i = 0; i < heap.length; i++) {
            heapInsert(heap, i);
            heapSize++;
        }
        while (heapSize > 0) {
            System.out.println(heap[0]);
            swap(heap, 0, --heapSize);
            heapPop(heap, 0, heapSize);
        }
    }

}

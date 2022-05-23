package sort;

//选择排序、冒泡排序
public class SelectSort {

    //不稳定
    public static void selectSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    //稳定
    public static void bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j]>array[j+1]){
                        swap(array,j,j+1);
                }
            }
        }
    }


    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
//        selectSort(array);
        bubbleSort(array);
        System.out.println(array);
    }


}

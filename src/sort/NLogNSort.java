package sort;

public class NLogNSort {

    public static void mergeSort(int[] array, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, right, mid);
    }

    public static void merge(int[] array, int left, int right, int mid) {
        int length = right - left + 1;
        int[] newArray = new int[length];
        int index = 0;
        int L = left;
        int R = mid + 1;
        while (L <= mid && R <= right) {
            newArray[index++] = array[L] <= array[R] ? array[L++] : array[R++];
        }
        while (L <= mid) {
            newArray[index++] = array[L++];
        }
        while (R <= right) {
            newArray[index++] = array[R++];
        }

        for (int i = 0; i < length; i++) {
            array[left + i] = newArray[i];
        }
    }

    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        mergeSort(array, 0, array.length - 1);
        System.out.println("");
    }
}

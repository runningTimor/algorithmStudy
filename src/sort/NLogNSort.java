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


    //小和问题
    public static int getSmallSum(int[] array, int left, int right) {
        int res = 0;
        if (left == right) {
            return res;
        }
        int mid = left + ((right - left) >> 1);
        res += getSmallSum(array, left, mid);
        res += getSmallSum(array, mid + 1, right);
        //合并过程发现的逆序对
        res += mergePair(array,left,right,mid);
        return res;
    }

    public static int mergePair(int[] array, int left, int right, int mid) {
        int length = right - left + 1;
        int L = left;
        int R = mid + 1;
        int index = 0;
        int res = 0;
        int[] newArray = new int[length];
        while (L <= mid && R <= right) {
            if (array[L] < array[R]) {
                res += array[L] * (right - R + 1);
                newArray[index++] = array[L++];
            } else {
                newArray[index++] = array[R++];
            }
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
        return res;
    }

    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1,2};
//        mergeSort(array, 0, array.length - 1);
        System.out.println(getSmallSum(array,0,array.length-1));
    }
}

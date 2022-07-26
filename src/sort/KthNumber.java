package sort;

/**
 * @author: renjie
 * @description: 求数组中第K大的数
 * @date: 2022/7/24 11:49
 */
public class KthNumber {

    public static int getKthNumber(int[] array, int k) {

        return process(array, 0, array.length - 1, k);
    }

    public static int process(int[] array, int start, int end, int k) {
        int privot = array[end];
        int offset = 0;
        for (int index = start; index <= end; index++) {

            if (array[index] > privot) {
                swap(array, index, start + offset);
                offset++;
            }
        }
        swap(array, end, start + offset);
        if (k == offset + 1) {
            return array[start + offset];
        } else if (k < offset + 1) {
            return process(array, start, start + offset - 1, k);
        } else {
            return process(array, start + offset + 1, end, k - offset - 1);
        }


    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 8, 9, 5, 6, 4, 7, 1};
        int k = 3;
        System.out.println(getKthNumber(array, k));
    }
}

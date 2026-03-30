package other;


import java.util.Arrays;

/**
 * 数组array中可以array中任意数字开头 任意数字结尾的数值对组成的两位数 第K大是那个数？
 */
public class KthPair {

    /**
     * 改进快排求第K小数
     *
     * @param array
     * @param k
     * @return
     */
    public static int getKthNum(int[] array, int k) {

        if (array == null) {
            return -1;
        }
        if (k > array.length || k < 1) {
            return -1;
        }

        return process(array, 0, array.length - 1, k - 1);

    }

    public static int process(int[] array, int l, int r, int k) {

        int[] partition = partition(array, l, r);
        int res = 0;

        if (partition[0] <= k && partition[1] >= k) {
            res = array[k];
        } else if (partition[0] > k) {
            res = process(array, l, partition[0] - 1, k);
        } else if (partition[1] < k) {
            res = process(array, partition[1] + 1, r, k);
        }

        return res;
    }


    public static int[] partition(int[] array, int L, int R) {
        int privot = L + (int) (Math.random() * (R - L + 1));
        int[] range = new int[2];
        int Less = L - 1;
        int more = R + 1;
        int index = L;
        while (index < more) {
            if (array[index] > array[privot]) {
                swap(array, index, --more);
            }
            if (array[index] == array[privot]) {
                index++;
            }
            if (array[index] < array[privot]) {
                swap(array, index++, ++Less);
            }
        }
        range[0] = Less + 1;
        range[1] = more - 1;
        return range;
    }

    public static void swap(int[] array, int indexA, int indexB) {
        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 7, 9, 8, 6, 4, 2};
        System.out.println(getKthNum(array, 5));
        System.out.println(Arrays.toString(array));
    }
}

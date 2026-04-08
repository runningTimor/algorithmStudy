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


    public int kthNum(int[] array, int k) {
        if (array == null || array.length == 0 || array.length < k) {
            throw new RuntimeException("不存在");
        }
        return bfprt(array, 0, array.length - 1, k - 1);
    }

    public int bfprt(int[] array, int left, int right, int k) {
        if (left == right) {
            return array[left];
        }
        int privot = getMedianOfMedians(array, left, right);
        int[] partitionArray = partition(array, left, right, privot);
        if (k >= partitionArray[0] && k <= partitionArray[1]) {
            return array[partitionArray[0]];
        } else if (k < partitionArray[0]) {
            return bfprt(array, left, partitionArray[0] - 1, k);
        } else {
            return bfprt(array, partitionArray[1] + 1, right, k);
        }
    }

    private int[] partition(int[] array, int left, int right, int privot) {
        int index = left;
        int minorRight = left - 1;
        int majorLeft = right + 1;
        while (index < majorLeft) {
            if (array[index] < privot) {
                swap(array, ++minorRight, index++);
            } else if (array[index] == privot) {
                index++;
            } else {
                swap(array, --majorLeft, index);
            }
        }
        int[] partition = new int[2];
        partition[0] = minorRight + 1;
        partition[1] = majorLeft - 1;
        return partition;
    }

    private int getMedianOfMedians(int[] array, int left, int right) {
        int size = right - left + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int N = size / 5 + offset;
        int[] median = new int[N];
        for (int index = 0; index < N; index++) {
            int startIndex = 5 * index;
            int endIndex = Math.min(startIndex + 4, right);
            insertSort(array, startIndex, endIndex);
            int midIndex = (startIndex + endIndex) / 2;
            median[index] = array[midIndex];
        }
        return bfprt(median, 0, median.length - 1, median.length / 2);
    }

    private void insertSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            for (int j = i; j > left && array[j] < array[j - 1]; j--) {
                swap(array, j, j - 1);
            }
        }
    }
    public static void main(String[] args) {

        int[] array = {1, 2, 3, 8, 9, 5, 6, 4, 7, 1};
        int k = 3;
        System.out.println(getKthNumber(array, k));
    }
}

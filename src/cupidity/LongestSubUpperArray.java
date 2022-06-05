package cupidity;

/**
 * 最长的递增子序列长度
 */
public class LongestSubUpperArray {

    public static int getLongestSubArrayLengh(int[] arrays) {
        if (arrays == null || arrays.length == 0) {
            return 0;
        }
        //长度为i+1的递增子序列中，最小结尾元素
        int[] minEnds = new int[arrays.length];
        minEnds[0] = arrays[0];
        //minEnds数组中已经填充最末尾的元素的索引
        int endIndex = 0;
        for (int num : arrays) {
            if (num > minEnds[endIndex]) {
                //填充minEnds数组
                minEnds[++endIndex] = num;
            } else {
                int index = binarySearch(minEnds, num, 0, endIndex);
                //找到minEnds数组中大于等于num的最左的数，替换
                minEnds[index] = num;
            }
        }

        return endIndex+1;
    }

    /**
     * 在有序数组中二分查找大于等于num的数的位置
     *
     * @param arrays
     * @param searchNum
     * @return
     */
    public static int binarySearch(int[] arrays, int searchNum, int startIndex, int endIndex) {
        if (arrays == null || arrays.length == 0 ||
                startIndex >= arrays.length || endIndex >= arrays.length) {
            return -1;
        }
        int L = startIndex;
        int R = endIndex;
        int locateIndex = -1;
        while (R >= L) {
            int mid = L + ((R - L) >> 1);
            if (arrays[mid] >= searchNum) {
                locateIndex = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return locateIndex;
    }


    public static void main(String[] args) {
//        int[] arrays = {1, 2, 3, 4, 5, 7, 8, 9};
        int[] arrays = {3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};
        System.out.println(binarySearch(arrays, 6, 0, 7));
        System.out.println(getLongestSubArrayLengh(arrays));

    }
}

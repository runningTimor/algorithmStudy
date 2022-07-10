package unsort;

/**
 * @author: renjie
 * @description: 完美洗牌问题
 * @date: 2022/7/9 22:34
 */
public class PerfectShuffle {

    //数组长度为偶数2N，第i张牌调整的目标位置规律
    public static int getNewIndex(int N, int i) {
        //所有下标从0开始
        if (i <= N) {
            return 2 * i;
        } else {
            return 2 * (i - N) - 1;
        }
    }

    public static void shuffle(int[] arrar, int L, int R) {
        while (L <= R) {
            //切成一块一块解决，每一块的长度为3的K次幂-1
            int length = R - L + 1;
            int k = 1;
            int base = 3;
            while (base <= (length + 1) / 3) {
                base *= 3;
                k++;
            }
            //每块的调整起始位置为3的（K-1）次幂，每块长度为base-1
            int mid = (L + R) / 2;
            int blockSize = (base - 1) / 2;
            //需要进行旋转的左部分为[L+blockSize,mid],需要旋转的右部分为[mid+1,mid+blockSize]
            roate(arrar, L + blockSize, mid, mid + blockSize);
            //旋转完成后，从L开始，在长度为base-1的范围上进行下标连续推
            cycles(arrar, L, base - 1, k);
            L += base - 1;
        }
    }

    //要调整两块的相对位置，先把左右两部分各自逆序，然后再整体逆序
    public static void roate(int[] array, int left, int mid, int right) {
        //先左半部分逆序
        reverse(array, left, mid);
        //右半部逆序
        reverse(array, mid + 1, right);
        reverse(array, left, right);
    }

    public static void reverse(int[] array, int L, int R) {
        while (L < R) {
            swap(array, L++, R--);
        }
    }

    public static void swap(int[] array, int L, int R) {
        int temp = array[L];
        array[L] = array[R];
        array[R] = temp;
    }

    //在from+length的范围下标连续推
    public static void cycles(int[] array, int from, int length, int k) {
        //找到每个环的初始位置，一共K个
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int preValue = array[from + trigger - 1];
            int newIndex = getNewIndex(length / 2, trigger);
            while (newIndex != trigger) {
                int temp = array[from + newIndex - 1];
                array[from + newIndex - 1] = preValue;
                preValue = temp;
                newIndex = getNewIndex(length / 2, newIndex);

            }
            array[from + newIndex - 1] = preValue;
        }
    }


    public static void main(String[] args) {

        int[] array = {2, 4, 6, 1, 3, 5};
        shuffle(array, 0, array.length - 1);
        System.out.println("");
    }

}

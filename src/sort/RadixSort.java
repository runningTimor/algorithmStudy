package sort;

//基数排序
public class RadixSort {

    //求一个数的进制位
    public static int getDigit(int num) {
        int digit = 0;
        while (num != 0) {
            digit++;
            num /= 10;
        }
        return digit;
    }

    public static void radixSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int a : array) {
            if (a > max) {
                max = a;
            }
        }
        int radix = getDigit(max);
        //最大的数有多少个位，排序就要进出桶几次
        for (int i = 1; i <= radix; i++) {
            //统计当前进制位上数字出现的次数(用count数组替代一次出桶和入桶)
            int[] count = new int[10];
            for (int a : array) {
                count[getDigitNum(a, radix)]++;
            }
            //加工count数组，进制radix位上的数字小于等于i的总数量
            for (int index = 1; index < count.length; index++) {
                count[index] += count[index - 1];
            }
            //按radix进制位进行排序（需要从原数组的右到左进行遍历）
            int[] temp = new int[array.length];
            for (int index = array.length - 1; index >= 0; index--) {
                int newIndex = --count[getDigitNum(array[index], radix)];
                temp[newIndex] = array[index];
            }
            //将临时桶中的数倒回原桶
            for (int index = 0; index < array.length; index++) {
                array[index] = temp[index];
            }
        }
    }

    //取出一个数字在某进制位上的数字
    public static int getDigitNum(int num, int radix) {
        while (radix > 1) {
            num /= 10;
            radix--;
        }
        return num % 10;
    }

    public static void main(String[] args) {
        System.out.println(getDigit(10));
        System.out.println(getDigitNum(12, 2));

        int[] array = {1, 2, 5, 4, 6, 9, 8, 7};
        radixSort(array);
        System.out.println();
    }

}

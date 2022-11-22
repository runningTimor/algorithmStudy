package xor;

/**
 * @author: renjie
 * @description: 找到出现K次的数
 * 一个数据，只有一种数出现K次，其余数出现M次（K<M）
 * @date: 2022/11/22 21:41
 */
public class FindKthNumber {

    public static int find(int[] array, int k, int m) {
        int[] byteCount = new int[32];
        //取出每个数在每个二进制为上的值
        int byteNumSupport = 1;
        for (int byteIndex = 0; byteIndex < 32; byteIndex++) {
            for (int num : array) {
                if ((num & byteNumSupport) != 0) {
                    byteCount[byteIndex]++;
                }
            }
            byteNumSupport = byteNumSupport << 1;
        }
        byteNumSupport = 1;
        int kThNum = 0;
        for (int byteIndex = 0; byteIndex < 32; byteIndex++) {
            if (byteCount[byteIndex] % m != 0) {
                kThNum |= byteNumSupport;
            }
            byteNumSupport = byteNumSupport << 1;
        }
        return kThNum;

    }

    public static void main(String[] args) {
        int[] array = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3,3,4};
        System.out.println(find(array, 1, 4));
    }
}

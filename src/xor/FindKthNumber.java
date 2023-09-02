package xor;

import java.util.HashMap;
import java.util.HashSet;

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

    // 传统解法
    public static int findClasical(int[] array, int k, int m) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : array) {
            if (countMap.containsKey(num)) {
                countMap.put(num, countMap.get(num) + 1);
            } else {
                countMap.put(num, 1);
            }
        }
        for (Integer key : countMap.keySet()) {
            if (countMap.get(key) == k) {
                return key;
            }
        }
        return -1;

    }

    /**
     * 生成随机数组
     *
     * @param kinds 数组元素种类数
     * @param range 元素值范围
     * @param k
     * @param m
     * @return
     */
    public static int[] randomArray(int kinds, int range, int k, int m) {

        int randomKinds = (int) (Math.random() * kinds) + 2;
        int[] array = new int[k + (randomKinds - 1) * m];
        int kNum = randomNum(range);
        int index = 0;
        while (index < k) {
            array[index++] = kNum;
        }
        HashSet<Integer> numSet = new HashSet<>(randomKinds);
        numSet.add(kNum);
        for (int i = 1; i <= randomKinds-1; i++) {

            int mNum = 0;
            do {
                mNum = randomNum(range);
            } while (numSet.contains(mNum));
            numSet.add(mNum);
            while (index < k + i * m) {
                array[index++] = mNum;
            }
        }

        // 乱序
        for (int i = 0;i<array.length;i++){
            for (int j = 0; j< (int)(Math.random()*i);j++){
                int temp = array[i];
                array[i]= array[j];
                array[j] = temp;
            }
        }

        return array;
    }

    public static int randomNum(int range) {
        return (int) (Math.random() * range) - (int) (Math.random() * range);
    }


    public static void main(String[] args) {

        int times = 10000;
        int numKinds = 100;
        int range = 200;
        int a = (int) (Math.random() * 9) + 1;
        int b = (int) (Math.random() * 9) + 1;
        int k = Math.min(a, b);
        int m = Math.max(a, b);
        if (k == m) {
            m++;
        }

        for (int i = 0; i < times; i++) {

            int[] array = randomArray(numKinds, range, k, m);
            int ans1 = find(array, k, m);
            int ans2 = findClasical(array, k, m);
            if (ans1 != ans2) {
                System.out.println("出错了");
            }
        }
        System.out.println("程序正确");

    }
}

package dynamicProcess;

import java.util.HashMap;

/**
 * @author: renjie
 * @description: 子数组异或和为零的最多不重叠部分
 * @date: 2022/7/2 17:25
 */
public class SubArrayEor {

    //以i位置元素所在分组异或和是否为零进行划分
    public static int getMax(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        //dp[i]表示原数组中0-i的数最多有多少个异或和为零的不重叠部分
        int[] dp = new int[array.length];
        dp[0] = array[0] == 0 ? 1 : 0;
        //前缀异或和
        int eor = array[0];
        //前缀异或和为key的最近一次索引为value
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        indexMap.put(0, -1);
        indexMap.put(eor, 0);
        for (int i = 1; i < array.length; i++) {
            eor ^= array[i];
            //1、以i位置结尾数组中最佳划分，array[i]所在分组异或和不为零
            dp[i] = dp[i - 1];
            //2、以i位置结尾数组中最佳划分，array[i]所在分组异或和为零
            if (indexMap.containsKey(eor)) {
                int lastIndex = indexMap.get(eor);
                dp[i] = Math.max(dp[i], lastIndex >= 0 ? dp[lastIndex] + 1 : 1);
            }
            indexMap.put(eor, i);
        }
        return dp[array.length - 1];

    }

    public static void main(String[] args) {
        int[] array = {3, 2, 1, 0, 1, 0, 2, 0, 3, 2, 1, 0, 4, 0};
        System.out.println(getMax(array));

    }
}

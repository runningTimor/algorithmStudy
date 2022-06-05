package dynamicProcess;

/**
 * @author renjie
 * @description 背包能装货物的最大值
 * @date 2022/6/5 21:14
 */
public class MaxBagValue {

    public static int getMaxValue(int[] weight, int[] value, int bagCapacity) {
        int res1 = process(weight, value, 0, 0, bagCapacity);
        int res2 = dp(weight, value, bagCapacity);
        System.out.println(res1);
        System.out.println(res2);
        return res2;

    }

    //i 及以后的选择能达到最大的价值
    public static int process(int[] weight, int[] value, int i, int alreadyWeight, int bagCapacity) {
        if (alreadyWeight > bagCapacity) {
            return 0;
        }
        if (i == weight.length) {
            return 0;
        }
        int res = 0;
        if (alreadyWeight + weight[i] <= bagCapacity) {

            res = value[i] + process(weight, value, i + 1, alreadyWeight + weight[i], bagCapacity);
        }
        res = Math.max(res,process(weight, value, i + 1, alreadyWeight, bagCapacity));
        return res;
    }

    public static int dp(int[] weight, int[] value, int bagCapacity) {
        int[][] dp = new int[weight.length + 1][bagCapacity + 1];
        for (int i = 0; i <= bagCapacity; i++) {
            dp[weight.length][i] = 0;
        }
        for (int i = 0; i <= weight.length; i++) {
            dp[i][bagCapacity] = 0;
        }
        for (int r = weight.length - 1; r >= 0; r--) {
            for (int j = bagCapacity; j >= 0; j--) {
                if (j + weight[r] > bagCapacity) {
                    dp[r][j] = dp[r + 1][j];
                } else {
                    dp[r][j] = Math.max(dp[r + 1][j], dp[r + 1][j + weight[r]] + value[r]);
                }
            }
        }
        return dp[0][0];

    }

    public static void main(String[] args) {
        int[] weight = {1, 2, 5};
        int[] value = {20, 30, 100};
        getMaxValue(weight, value, 2);
    }
}

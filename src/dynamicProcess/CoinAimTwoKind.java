package dynamicProcess;

/**
 * @author: renjie
 * @description: 两种货币组合成aim的方法数
 * 第一类货币可以使用任意个
 * 第二类货币仅能使用一个
 * @date: 2022/7/2 20:11
 */
public class CoinAimTwoKind {

    public static int getTotalWay(int[] coin1, int[] coin2, int aim) {
        int[] dp1 = getWay1(coin1, aim);
        int[] dp2 = getWay2(coin2, aim);
        int res = 0;
        for (int i = 0; i <= aim; i++) {
            //第一组硬币组合i金钱数
            res += dp1[i] * dp2[aim - i];
        }
        return res;

    }

    //使用coin中的硬币任意个组成目标金钱的总方法数
    public static int[] getWay1(int[] coin, int aim) {
        if (coin == null || coin.length == 0) {
            return new int[aim + 1];
        }
        int length = coin.length;
        //dp[i][j]表示coin数组中i及其以后的硬币随便使用组成j的总方法数
        int[][] dp = new int[length + 1][aim + 1];
        dp[length][0] = 1;
        for (int r = length - 1; r >= 0; r--) {
            for (int c = 0; c <= aim; c++) {
                dp[r][c] += dp[r + 1][c];
                if (c - coin[r] >= 0) {
                    dp[r][c] += dp[r][c - coin[r]];
                }
            }
        }
        return dp[0];
    }

    //使用coin中的每种硬币最多一个组成目标金钱的总方法数
    public static int[] getWay2(int[] coin, int aim) {

        int length = coin.length;
        //dp[i][j]表示coin数组中i及其以后的硬币组成j的总方法数
        int[][] dp = new int[length + 1][aim + 1];
        dp[length][0] = 1;
        for (int r = length - 1; r >= 0; r--) {
            for (int c = 0; c <= aim; c++) {
                dp[r][c] = dp[r + 1][c];
                if (c >= coin[r]) {
                    dp[r][c] = dp[r + 1][c - coin[r]];
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {

    }
}

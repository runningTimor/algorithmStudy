package dynamicProcess;

/**
 * @author renjie
 * @description 无限制硬币使用次数，组成目标金额的方法数
 * @date 2022/6/11 13:08
 */
public class CoinAim {

    public static int process(int[] coins, int rest, int index) {

        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int count = 0; coins[index] * count <= rest; count++) {
            ways += process(coins, rest - coins[index] * count, index + 1);
        }
        return ways;
    }

    public static int dp(int[] coins, int rest) {
        int N = coins.length;
        int[][] dp = new int[N + 1][rest + 1];
        dp[N][0] = 1;

        for (int r = N - 1; r >= 0; r--) {
            for (int c = 0; c <= rest; c++) {
                dp[r][c] = dp[r + 1][c];
                if (c - coins[r] >= 0) {
                    dp[r][c] += dp[r][c - coins[r]];
                }
            }
        }
        return dp[0][rest];
    }

    public static void main(String[] args) {

        int[] coins = {1, 2, 3, 5};
        int aim = 2;
        System.out.print(process(coins, aim, 0));
        System.out.print(dp(coins, aim));
    }
}

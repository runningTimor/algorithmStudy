package dynamicProcess;

/**
 * @author renjie
 * @description 组成目标金钱数的最少硬币数
 * @date 2022/6/10 21:08
 */
public class CoinAimNoRepeat {

    public static int process(int[] coins, int rest, int currentIndex) {
        if (rest < 0) {
            return -1;
        }
        if (rest == 0) {
            return 0;
        }
        if (currentIndex == coins.length) {
            return -1;
        }
        int coin1 = process(coins, rest, currentIndex + 1);
        int coin2 = process(coins, rest - coins[currentIndex], currentIndex + 1);
        if (coin1 == -1 && coin2 == -1) {
            return -1;
        } else {
            if (coin1 == -1) {
                return coin2 + 1;
            }
            if (coin2 == -1) {
                return coin1;
            }
            return Math.min(coin1, coin2 + 1);
        }
    }

    public static int dp(int[] coins, int rest, int currentIndex) {
        int[][] dp = new int[coins.length + 1][rest + 1];
        for (int c = 1; c <= rest; c++) {
            dp[coins.length][c] = -1;
        }
        for (int r = coins.length - 1; r >= 0; r--) {
            for (int c = 1; c <= rest; c++) {
                int way1 = dp[r + 1][c];
                int way2 = -1;
                if (c - coins[r] >= 0) {
                    way2 = dp[r + 1][c - coins[r]];
                }
                if (way1 == -1 && way2 == -1) {
                    dp[r][c] = -1;
                } else {
                    if (way1 == -1) {
                        dp[r][c] = way2 + 1;
                    } else if (way2 == -1) {
                        dp[r][c] = way1;
                    } else {
                        dp[r][c] = Math.min(way1, way2 + 1);
                    }
                }
            }
        }
        return dp[currentIndex][rest];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3, 4};
        System.out.print(dp(coins, 5, 0));
    }

}

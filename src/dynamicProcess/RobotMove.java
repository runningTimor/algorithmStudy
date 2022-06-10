package dynamicProcess;

/**
 * @author renjie
 * @description 机器人运动步数
 * @date 2022/6/10 16:50
 */
public class RobotMove {

    public static int process(int barrier, int from, int to, int k) {
        if (k == 0) {
            return from == to ? 1 : 0;
        }
        if (from == barrier) {
            return process(barrier, from - 1, to, k - 1);
        }
        if (from == 1) {
            return process(barrier, from + 1, to, k - 1);
        }
        return process(barrier, from - 1, to, k - 1) + process(barrier, from + 1, to, k - 1);
    }

    public static int dp(int barrier, int from, int to, int k) {
        int[][] dp = new int[barrier + 1][k + 1];
        dp[to][0] = 1;
        for (int c = 1; c <= k; c++) {
            for (int r = 1; r <= barrier; r++) {
                if (r == barrier) {
                    dp[r][c] = dp[r - 1][c - 1];
                } else if (r == 1) {
                    dp[r][c] = dp[r + 1][c - 1];
                } else {
                    dp[r][c] = dp[r - 1][c - 1] + dp[r + 1][c - 1];
                }
            }
        }
        return dp[from][k];
    }

    public static void main(String[] args) {
        System.out.print(process(8, 2, 5, 5));
        System.out.print(dp(8, 2, 5, 5));

    }
}

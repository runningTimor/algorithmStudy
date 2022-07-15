package dynamicProcess;

/**
 * @author: renjie
 * @description: 从左上角到右下角的最小路径和
 * @date: 2022/7/15 11:30
 */
public class MinPath {

    public static int getMinPath(int[][] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int m = array.length;
        int n = array[0].length;
//        return array[0][0] + process(array, 0, 0, m, n);
        return dp(array, m, n);

    }

    public static int process(int[][] array, int r, int c, int m, int n) {
        if (r == m - 1 && c == n - 1) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        if (r < m - 1) {
            res = Math.min(res, array[r + 1][c] + process(array, r + 1, c, m, n));
        }
        if (c < n - 1) {
            res = Math.min(res, array[r][c + 1] + process(array, r, c + 1, m, n));
        }
        return res;
    }

    public static int dp(int[][] array, int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = array[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = array[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = array[i][0] + dp[i - 1][0];
        }
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[r][c] = array[r][c] + Math.min(dp[r - 1][c], dp[r][c - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }


    public static void main(String[] args) {

        int[][] array = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(getMinPath(array));
    }
}

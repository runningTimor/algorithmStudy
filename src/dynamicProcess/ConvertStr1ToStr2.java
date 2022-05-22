package dynamicProcess;

//将string1转换成string2，插入代价insert，替换代价replace，删除代价delete，返回最小代价
public class ConvertStr1ToStr2 {

    public static int getMinCost(String s1, String s2, int insert, int replace, int del) {
        if (s1 == null || s2 == null) {
            return -1;
        }
        int m = s1.length();
        int n = s2.length();
        //dp[i][j]的含义为：string1从0到i-1的部分变成string2从0到j-1部分最小的代价
        int[][] dp = new int[m + 1][n + 1];
        for (int row = 0; row <= m; row++) {
            for (int cow = 0; cow <= n; cow++) {
                if (row == 0) {
                    dp[0][cow] = cow * insert;
                } else if (cow == 0) {
                    dp[row][0] = row * del;
                } else {
                    dp[row][cow] = Integer.MAX_VALUE;
                }
            }
        }

        //一共有三种转变方法，取最小代价
        for (int row = 1; row <= m; row++) {
            for (int cow = 1; cow <= n; cow++) {
                dp[row][cow] = Math.min(dp[row][cow], dp[row][cow - 1]+insert);
                dp[row][cow] = Math.min(dp[row][cow], dp[row - 1][cow]+del);
                if (s1.charAt(row-1) == s2.charAt(cow-1)) {
                    dp[row][cow] = Math.min(dp[row][cow], dp[row - 1][cow-1]);

                } else {
                    dp[row][cow] = Math.min(dp[row][cow], dp[row - 1][cow-1]+replace);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(getMinCost(s1,s2,5,2,3));
    }

}

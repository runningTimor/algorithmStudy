package dynamicProcess;

/**
 * @author: renjie
 * @description: 合法括号子串的最大长度
 * @date: 2022/6/26 17:32
 */
public class MaxBracketLength {

    //动态规划解法
    public static int getMaxLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        char[] chars = s.toCharArray();
        //dp[i]含义为以i位置结尾的最长合法括号子串的长度
        int[] dp = new int[length];
        int maxLength = 0;
        for (int i = 1; i < length; i++) {
            if (')' == chars[i]) {
                //与当前位置进行比较的位置
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && '(' == chars[pre]) {
                    dp[i] = dp[i - 1] + 2;
                    if (pre > 0) {
                        dp[i] = dp[i] + dp[pre - 1];
                    }
                }
            }
            maxLength = Math.max(maxLength,dp[i]);
        }
        return maxLength;
    }

    public static void main(String[] args) {

        String s = "((((()))))()";
        System.out.println(getMaxLength(s));
    }

}

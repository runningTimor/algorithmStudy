package dynamicProcess;

import java.util.Arrays;
import java.util.List;

//应用“0”，“1”，“&”，“|”，“^“五种符号组成期望的布尔值有多少种方法
public class BooleanDesire {

    public static final List<String> logicSymbol = Arrays.asList(new String[]{"&", "|", "^"});
    public static final List<String> numberSymbol = Arrays.asList(new String[]{"0", "1"});

    public static boolean valid(String express) {

        if ((express.length() & 1) == 0) {
            return false;
        }

        for (int i = 0; i < express.length(); i += 2) {
            if (!numberSymbol.contains(String.valueOf(express.charAt(i)))) {
                return false;
            }
        }
        for (int i = 1; i < express.length() - 1; i += 2) {
            if (!logicSymbol.contains(String.valueOf(express.charAt(i)))) {
                return false;
            }
        }
        return true;
    }


    public static int ways(String express, boolean desire) {
        if (!valid(express)) {
            return 0;
        }
        return getWays(0, express.length() - 1, desire, express);
    }


    public static int getWays(int L, int R, boolean desire, String express) {
        if (L == R) {
            if (express.charAt(L) == '1') {
                return desire ? 1 : 0;
            } else {
                return desire ? 0 : 1;
            }
        }

        //每个符号最后组合
        int ans = 0;
        for (int i = L + 1; i < R; i += 2) {
            if (desire) {
                switch (express.charAt(i)) {
                    case '&':
                        ans += getWays(L, i - 1, true, express) * getWays(i + 1, R, true, express);
                        break;
                    case '|':
                        ans += getWays(L, i - 1, true, express) * getWays(i + 1, R, true, express);
                        ans += getWays(L, i - 1, true, express) * getWays(i + 1, R, false, express);
                        ans += getWays(L, i - 1, false, express) * getWays(i + 1, R, true, express);
                        break;
                    case '^':
                        ans += getWays(L, i - 1, true, express) * getWays(i + 1, R, false, express);
                        ans += getWays(L, i - 1, false, express) * getWays(i + 1, R, true, express);
                        break;
                }
            } else {
                switch (express.charAt(i)) {
                    case '&':
                        ans += getWays(L, i - 1, false, express) * getWays(i + 1, R, false, express);
                        ans += getWays(L, i - 1, false, express) * getWays(i + 1, R, true, express);
                        ans += getWays(L, i - 1, true, express) * getWays(i + 1, R, false, express);
                        break;
                    case '|':
                        ans += getWays(L, i - 1, false, express) * getWays(i + 1, R, false, express);
                        break;
                    case '^':
                        ans += getWays(L, i - 1, true, express) * getWays(i + 1, R, true, express);
                        ans += getWays(L, i - 1, false, express) * getWays(i + 1, R, false, express);
                        break;
                }
            }
        }
        return ans;
    }

    //改动态规划
    public static int dp(String express, boolean desire) {
        int length = express.length();
        int[][] trueMap = new int[length][length];
        int[][] falseMap = new int[length][length];
        for (int L = 0; L < length; L += 2) {
            trueMap[L][L] = express.charAt(L) == '1' ? 1 : 0;
            falseMap[L][L] = express.charAt(L) == '0' ? 1 : 0;
        }

        for (int L = length - 3; L >= 0; L -= 2) {
            for (int R = L + 2; R < length; R += 2) {
                for (int index = L + 1; index < R; index += 2) {
                    switch (express.charAt(index)) {
                        case '&':
                            trueMap[L][R] += trueMap[L][index - 1] * trueMap[index + 1][R];
                            break;
                        case '|':
                            trueMap[L][R] += trueMap[L][index - 1] * trueMap[index + 1][R];
                            trueMap[L][R] += trueMap[L][index - 1] * falseMap[index + 1][R];
                            trueMap[L][R] += falseMap[L][index - 1] * trueMap[index + 1][R];
                            break;
                        case '^':
                            trueMap[L][R] += trueMap[L][index - 1] * falseMap[index + 1][R];
                            trueMap[L][R] += falseMap[L][index - 1] * trueMap[index + 1][R];
                            break;
                    }
                    switch (express.charAt(index)) {
                        case '&':
                            falseMap[L][R] += falseMap[L][index - 1] * falseMap[index + 1][R];
                            falseMap[L][R] += falseMap[L][index - 1] * trueMap[index + 1][R];
                            falseMap[L][R] += trueMap[L][index - 1] * falseMap[index + 1][R];
                            break;
                        case '|':
                            falseMap[L][R] += falseMap[L][index - 1] * falseMap[index + 1][R];
                            break;
                        case '^':
                            falseMap[L][R] += trueMap[L][index - 1] * trueMap[index + 1][R];
                            falseMap[L][R] += falseMap[L][index - 1] * falseMap[index + 1][R];
                            break;
                    }
                }
            }
        }
        return desire ? trueMap[0][length - 1] : falseMap[0][length - 1];
    }


    public static void main(String[] args) {
        String express = "1^0|0|1";
//        String express = "1";
        int way1 = ways(express, false);
        int way2 = dp(express, false);
        System.out.println(way1 == way2);
    }

}

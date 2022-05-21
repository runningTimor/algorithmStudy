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
            return desire ? (express.charAt(L) == '1' ? 1 : 0) : express.charAt(L) == '0' ? 1 : 0;
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

    public static void main(String[] args) {
//        String express = "1^0|0|1";
        String express = "1";
        int way = ways(express, false);
        System.out.println(way);
    }

}

package string;

/**
 * @author: renjie
 * @description: 字符串正则匹配
 * @date: 2022/7/10 18:00
 */
public class StringRegMatch {

    public static boolean isMatch(String source, String reg) {
        if (!isValid(source, reg)) {
            return false;
        }
        return process(source.toCharArray(), reg.toCharArray(), 0, 0);
    }

    //用e字符数组ei及其以后能否匹配出 ，s字符数组中si及其以后
    public static boolean process(char[] s, char[] e, int si, int ei) {
        if (ei == e.length) {
            return si == s.length;
        }
        //以ei+1位置的字符是否为*做划分
        if (ei + 1 == e.length || e[ei + 1] != '*') {
            return si != s.length && (e[ei] == s[si] || e[ei] == '.') &&
                    process(s, e, si + 1, ei + 1);
        }
        //ei+1位置是*
        while (si != s.length && (s[si] == e[ei] || e[ei] == '.')) {
            if (process(s, e, si, ei + 2)) {
                return true;
            }
            si++;
        }
        return process(s, e, si, ei + 2);
    }


    public static boolean isValid(String source, String reg) {
        if (source == null || reg == null) {
            return false;
        }
        char[] sourceChar = source.toCharArray();
        for (char c : sourceChar) {
            if (c == '*' || c == '.') {
                return false;
            }
        }
        char[] regChar = reg.toCharArray();
        for (int i = 0; i < reg.length(); i++) {
            if (regChar[i] == '*' && (i == 0 || regChar[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        String source = "aaab";
        String reg = ".*";
        System.out.println(isMatch(source, reg));
    }
}

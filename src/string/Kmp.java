package string;

/**
 * @author renjie
 * @description 字符串匹配kmp算法
 * @date 2022/6/7 11:32
 */
public class Kmp {

    public static int getStringIndex(String sourceStr, String desStr) {
        int[] next = getStringNextArray(desStr.toCharArray());
        int i = 0;
        int j = 0;
        while (i < sourceStr.length() && j < desStr.length()) {
            if (sourceStr.charAt(i) == desStr.charAt(j)) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        if (j == desStr.length()) {
            return i - desStr.length();
        } else {
            return -1;
        }

    }

    public static int[] getStringNextArray(char[] chars) {
        int[] next = new int[chars.length];
        if (chars.length == 1) {
            return new int[] {-1};
        }
        next[0] = -1;
        next[1] = 0;
        int index = 2;
        //compareIndex既是index-1位置的next数组值，又是比较位置,每次循环过程保留了前一个位置的next数组值
        int compareIndex = 0;
        while (index < chars.length) {
            if (chars[index - 1] == chars[compareIndex]) {
                next[index++] = ++compareIndex;
            } else if (compareIndex > 0) {
                compareIndex = next[compareIndex];
            } else {
                next[index++] = 0;
            }
        }

        return next;
    }

    public static void main(String[] args) {
        String s1 = "asdifsdffff";
        String s2 = "sdf";
        System.out.print(getStringIndex(s1,s2));
    }

}

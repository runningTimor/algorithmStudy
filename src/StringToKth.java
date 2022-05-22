/**
 * a到z是26个英文字母的字典序，现在题目要求按照这个字典序编号，
 * a到z就是1到26，然后，ab就是27，ac就是28，依次往后，
 * 但是，b开头时，因为是子序列，所以，只能是bc打头，
 * 不能有ba和bb的序列。现在，给你一个字符序列，
 * 请返回该序列的编号。
 */
public class StringToKth {

    /**
     * 长度为length的编码有多少种
     *
     * @param length
     * @return
     */
    public static int f(int length) {
        int sum = 0;
        for (int i = 1; i <= 26; i++) {
            sum += g(i, length);
        }
        return sum;
    }

    /**
     * 以第i号字符开头的长度为length的编码有多少种
     *
     * @param i
     * @param length
     * @return
     */
    public static int g(int i, int length) {
        if (length == 1) {
            return 1;
        }
        int sum = 0;
        for (int index = i + 1; index <= 26; index++) {
            sum += g(index, length - 1);
        }
        return sum;
    }

    public static int kth(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] chars = s.toCharArray();
        int length = s.length();
        int sum = 0;
        //1、先算出比length短的字典序列有多少种
        for (int i = 1; i < length; i++) {
            sum += f(i);
        }
        //2、长度为length但首字母字典序小于字符串s首字母的编码有多少种
        int first = chars[0] - 'a' + 1;
        for (int i = 1; i < first; i++) {
            sum += g(i, length);
        }
        //3、以s首字母为首字母，长度为length的，字典序小于s的编码有多少种
        int preLimit = first;
        for (int index = 1; index < length; index++) {
            int endLimit = chars[index] - 'a' + 1;
            for (int i = preLimit + 1; i < endLimit; i++) {
                sum += g(i, length - index);
            }
            preLimit = endLimit;
        }
        return sum;
    }

    public static void main(String[] args) {
        String s = "ab";
        System.out.println(kth(s) + 1);
    }

}

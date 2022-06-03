package unsort;

public class ConvertStringToInteger {

    /**
     * 验证字符串的有效性
     * 1、首字符应该为非零数字或者负号，若为负号则第二位应为非零数字
     * 2、其他位为数字
     * 3、开始为零后续无字符
     *
     * @param s
     */
    public static boolean validString(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int length = s.length();
        char[] chars = s.toCharArray();
        if (chars[0] != '-' && (chars[0] < '0' || chars[0] > '9')) {
            return false;
        }
        if (chars[0] == '-' && (chars[1] == '0' || length == 1)) {
            return false;
        }
        if (chars[0] == '0' && length > 1) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * 将字符串转换成数字
     *
     * @param s
     * @return
     * @throws Exception
     */
    public static Integer covert(String s) throws RuntimeException {
        if (s == null || s.length() == 0) {
            throw new RuntimeException("不能转换！");
        }
        if (!validString(s)) {
            throw new RuntimeException("不能转换！");
        }
        char[] chars = s.toCharArray();
        boolean isNeg = chars[0] == '-';
        //用于判断转换过程是否溢出
        int q = Integer.MIN_VALUE / 10;
        int r = Integer.MIN_VALUE % 10;
        int result = 0;
        for (int i = isNeg ? 1 : 0; i < s.length(); i++) {
            int temp = '0' - chars[i];
            if ((result < q) || (result == q && temp < r)) {
                throw new RuntimeException("不能转换！");
            }
            result = result * 10 + temp;
        }
        if (!isNeg&&result==Integer.MIN_VALUE){
            throw new RuntimeException("不能转换！");
        }

        result = isNeg ? result : -result;
        return result;

    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(covert("2147483649"));
    }
}

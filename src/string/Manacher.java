package string;

/**
 * @author renjie
 * @description 回文半径数组
 * @date 2022/6/7 20:22
 */
public class Manacher {

    public static int getMaxLcpsLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] manacherStr = getManacherStr(s.toCharArray());
        //回文半径数组
        int[] array = new int[manacherStr.length];
        //遍历过的最右回文右边界索引
        int r = -1;
        //取得最右回文右边界时，圆心对应的索引+1
        int c = -1;
        int max = 0;
        for (int i = 0; i < manacherStr.length; i++) {
            array[i] = i < r ? Math.min(array[2 * c - i], r - i) : 1;
            while (i + array[i] < manacherStr.length && i - array[i] > -1) {
                if (manacherStr[i + array[i]] == manacherStr[i - array[i]]) {
                    array[i]++;
                } else {
                    break;
                }
            }
            //i位置的回文半径可以扩展，则更新c和r
            if (i + array[i] > r) {
                r = i + array[i];
                c = i;
                max = Math.max(max, array[i]);
            }
        }

        return max - 1;
    }

    //对源字符串每个字符之间填充字符
    public static char[] getManacherStr(char[] source) {
        char[] res = new char[2 * source.length + 1];
        int index = 0;
        for (int i = 0; i <= 2 * source.length; i++) {
            //偶数位置填充字符
            if ((i & 1) == 0) {
                res[i] = '#';
            } else {
                res[i] = source[index++];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String sourceStr = "werew";
//        System.out.print(getManacherStr(sourceStr.toCharArray()));
        System.out.print(getMaxLcpsLength(sourceStr));
    }

}

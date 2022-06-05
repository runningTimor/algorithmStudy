package unsort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renjie
 * @description 打印字符串的所有不重复全排列
 * @date 2022/6/5 18:28
 */
public class PrintAllPermutation {

    public static void process(char[] chars, int i, List<String> res) {
        if (i == chars.length) {
            res.add(String.valueOf(chars));
            return;
        }
        //在i位置上进行尝试,i位置经使用过的字符不再使用
        boolean[] isUsed = new boolean[26];
        for (int j = i; j < chars.length; j++) {
            if (!isUsed[chars[j] - 'a']) {
                isUsed[chars[j] - 'a'] = true;
                swap(chars, i, j);
                process(chars, i + 1, res);
                swap(chars, i, j);
            }
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String s = "aba";
        char[] chars = s.toCharArray();
        List<String> stringList = new ArrayList<>();
        process(chars, 0, stringList);
        System.out.println("");
    }
}

package unsort;

import java.util.HashMap;

//最长不重复子字符串长度
public class LongestNotRepeatSubString {

    public static int process(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxLength = 0;
        //前一个字符为结尾时，最长不重复子字符串的开始字符索引
        int startIndex = 0;
        //上一次出现字符的索引
        HashMap<Character, Integer> charIndexMap = new HashMap();
        //每个以i位置字符为结尾的最长不重复子字符串
        for (int i = 0; i < chars.length; i++) {
            int preCharIndex = -1;
            if (charIndexMap.containsKey(chars[i])) {
                preCharIndex = charIndexMap.get(chars[i]);
            }
            startIndex = Math.max(startIndex, preCharIndex+1);
            charIndexMap.put(chars[i], i);
            maxLength = Math.max(maxLength, i - startIndex+1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(process("abcabcbb"));
        System.out.println(process("bb"));
        System.out.println(process("pwwwkew"));
    }

}

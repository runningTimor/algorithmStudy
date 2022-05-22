package cupidity;

//从字符串中选出最小字典序的不重复字符序列
public class MinDictSeqNotRepeatChar {

    public static String process(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        //统计每个字符出现的词频
        int[] characterCount = new int[256];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            characterCount[index]++;
        }

        //最小字典序字符所在索引
        int minAscaIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            minAscaIndex = s.charAt(i)<=s.charAt(minAscaIndex)?i:minAscaIndex;
            if (--characterCount[index] == 0) {
                break;
            } else {

            }
        }
        return s.charAt(minAscaIndex)+process(s.substring(minAscaIndex+1).replace(String.valueOf(s.charAt(minAscaIndex)),""));

    }

    public static void main(String[] args) {

        String s = "cbacbkefgh";
        System.out.println(process(s));
    }

}

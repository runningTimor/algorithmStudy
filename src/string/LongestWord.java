package string;

import java.util.Arrays;
import java.util.HashSet;

public class LongestWord {

    public static HashSet<String> wordSet;

    public static boolean componentWord(String word, HashSet wordSet) {
        int length = word.length();
        if ("".equals(word)||wordSet.contains(word)){
            return true;
        }
        for (int i = 1; i < length; i++) {
            if (wordSet.contains(word.substring(0, i)) && componentWord(word.substring(i), wordSet)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] arsg) {
        String[] words = new String[]{"cat","banana","dog","nana","walk","walker","dogwalker"};

        Arrays.sort(words,(a,b)->{
            return a.length()==b.length()?a.compareTo(b):b.length()-a.length();
        });
        wordSet = new HashSet<>(Arrays.asList(words));
        for (String s :words){
            int length = s.length();
            for (int i = 1; i<length;i++){
                if (wordSet.contains(s.substring(0,i))&&componentWord(s.substring(i),wordSet)){
                    System.out.println(s);
                }

            }
        }

        String s = "asddd";
        System.out.println(s.substring(s.length()-1));



    }
}

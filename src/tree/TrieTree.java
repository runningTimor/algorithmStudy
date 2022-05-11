package tree;

import java.util.Arrays;
import java.util.List;

public class TrieTree {

    public static class TrieNode {
        //通过节点数
        public int pass;
        public int end;
        public TrieNode[] nexts;
        //如果字符种类超出26种可以用treemap结构
//        public TreeMap<Character,TrieNode> next;

        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }

        public static class Trie {
            private TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            /***
             * 字符串插入前缀树
             * @param word
             */
            public void insert(String word) {
                if (word == null || word.length() == 0) {
                    return;
                }
                TrieNode trieNode = root;
                char[] characters = word.toCharArray();
                int index = 0;
                trieNode.pass++;

                for (char c : characters) {
                    index = c - 'a';
                    if (trieNode.nexts[index]==null){
                        trieNode.nexts[index] = new TrieNode();
                    }
                    trieNode = trieNode.nexts[index];
                    trieNode.pass++;
                }
                trieNode.end++;
            }

            /**
             * 查询字符串word在前缀树中出现过几次
             * @param word
             * @return
             */
            public int search(String word) {
                if (word == null || word.length() == 0) {
                    return 0;
                }
                int index = 0;
                char[] characters = word.toCharArray();
                TrieNode trieNode = root;
                for (char c : characters) {
                    index = c - 'a';
                    if (trieNode.nexts[index] == null) {
                        return 0;
                    }
                    trieNode = trieNode.nexts[index];
                }

                return trieNode.end;
            }

            /**
             * 查询前缀树中多少字符串以word为前缀
             */
            public int searchTrie(String word){
                if (word == null || word.length() == 0) {
                    return 0;
                }
                int index = 0;
                char[] characters = word.toCharArray();
                TrieNode trieNode = root;
                for (char c : characters) {
                    index = c - 'a';
                    if (trieNode.nexts[index] == null) {
                        return 0;
                    }
                    trieNode = trieNode.nexts[index];
                }
                return trieNode.pass;
            }

            /**
             * 从前缀树中删除word
             */
            public void delete(String word){
                if (search(word)==0){
                    return;
                }
                char[] characters = word.toCharArray();
                TrieNode trieNode = root;
                int index = 0;
                for (char c : characters) {
                    index = c - 'a';
                    trieNode.nexts[index].pass--;
                    if (trieNode.nexts[index].pass == 0) {
                        trieNode.nexts[index]=null;
                        return;
                    }
                    trieNode = trieNode.nexts[index];
                }
                trieNode.end--;
            }


        }
    }

    public static void main(String[] arsg) {
        String[] words = new String[]{"apple", "hard","man","god","godman","godless"};
        TrieNode.Trie trie = new TrieNode.Trie();
        for (String s : words){
            trie.insert(s);
        }
        trie.delete("god");
        int longestWord = trie.search("god");
        System.out.println(longestWord);
    }
}

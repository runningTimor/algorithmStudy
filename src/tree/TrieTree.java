package tree;

public class TrieTree {

    public static class TrieNode {
        //通过节点数
        public int pass;
        public int end;
        public TrieNode[] nexts;

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

            public void insert(String word) {
                if (word == null || word.length() == 0) {
                    return;
                }
                TrieNode trieNode = root;
                char[] characters = word.toCharArray();
                for (char c : characters) {
                    trieNode.pass++;
                }
            }
        }
    }

    public static void main(String[] arsg) {
        System.out.println("666");
    }
}

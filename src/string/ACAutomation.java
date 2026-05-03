package string;

import java.util.*;

public class ACAutomation {

    public class TrieTreeNode {
        public TrieTreeNode fail;
        public TrieTreeNode[] nexts;
        public boolean use;
        public String fullStr;
        public int pass;
        public int end;

        public TrieTreeNode() {
            nexts = new TrieTreeNode[26];
        }
    }

    public TrieTreeNode generateTrieTree(List<String> stringList) {
        TrieTreeNode root = new TrieTreeNode();
        for (String str : stringList) {
            TrieTreeNode current = root;
            current.pass++;
            for (char c : str.toCharArray()) {
                int index = c - 'a';
                if (current.nexts[index] == null) {
                    current.nexts[index] = new TrieTreeNode();
                }
                current = current.nexts[index];
                current.pass++;
            }
            current.fullStr = str;
            current.end++;
        }
        return root;
    }

    public void build(TrieTreeNode root) {
        Queue<TrieTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TrieTreeNode current = queue.poll();
            for (int index = 0; index < 26; index++) {
                if (current.nexts[index] == null) {
                    continue;
                }
                current.nexts[index].fail = root;
                TrieTreeNode fail = current.fail;
                while (fail != null) {
                    if (fail.nexts[index] != null) {
                        current.nexts[index].fail = fail.nexts[index];
                        break;
                    }
                    fail = fail.fail;
                }
                queue.add(current.nexts[index]);
            }
        }
    }

    public List<String> match(TrieTreeNode root, String matcher) {
        List<String> ans = new ArrayList<>();
        TrieTreeNode current = root;
        for (char c : matcher.toCharArray()) {
            int index = c - 'a';
            while (current.nexts[index] == null && current != root) {
                current = current.fail;
            }
            current = current.nexts[index] != null ? current.nexts[index] : root;

            TrieTreeNode follow = current;
            while (follow != root) {
                if (follow.use) {
                    break;
                }
                if (follow.fullStr != null) {
                    follow.use = true;
                    ans.add(follow.fullStr);
                }
                follow = follow.fail;
            }

        }
        return ans;
    }

    // 测试示例
    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        List<String> patterns = Arrays.asList("he", "she", "his", "hers");
        TrieTreeNode root = ac.generateTrieTree(patterns);
        ac.build(root);

        String text = "ushers";
        List<String> result = ac.match(root, text);
        System.out.println("匹配结果：" + result); // 输出：[she, he, hers]
    }


}

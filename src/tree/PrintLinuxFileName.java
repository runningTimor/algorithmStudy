package tree;

import java.util.TreeMap;

/**
 * 打印Linux文件系统文件名称
 */
public class PrintLinuxFileName {

    /**
     * 前缀树结构
     * 非经典结构，值在节点上
     */
    public static class TrieTreeNode{
        private String name;
        private TreeMap<String,TrieTreeNode> nextMap;

        public TrieTreeNode(String name) {
            this.name = name;
            this.nextMap = new TreeMap<>();
        }
    }

    /**
     * 生成前缀树结构
     *
     * @param filePathName
     * @return
     */
    public static void generateTrieTree(String filePathName, TrieTreeNode head) {

        if (filePathName == null || filePathName == "") {
            return;
        }
        //按双斜线切割
        String[] fileName = filePathName.split("\\\\");
        TrieTreeNode current = head;
        for (String s : fileName) {
            TreeMap<String, TrieTreeNode> nextMap = current.nextMap;
            if (!nextMap.containsKey(s)) {
                nextMap.put(s, new TrieTreeNode(s));
            }
            current = nextMap.get(s);
        }
    }

    /**
     * 深度优先遍历打印文件名
     */
    public static void printFileName(TrieTreeNode head,int level){
            if (head==null || head.nextMap==null){
                return;
            }
        TreeMap<String,TrieTreeNode> nextMap = head.nextMap;
        for (TrieTreeNode node: nextMap.values()){
            System.out.println(generateSpace(level)+node.name);
            printFileName(node,level+1);
        }

    }

    public static String generateSpace(int level){
        String res = "";
        for (int i = 0; i<level;i++){
            res+= "  ";
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strings = {"c\\user\\renjie","c\\user\\desktop","d\\test"};
        TrieTreeNode head = new TrieTreeNode("");
        for (String filePath :strings){
            generateTrieTree(filePath,head);
        }
        printFileName(head,0);

    }

}

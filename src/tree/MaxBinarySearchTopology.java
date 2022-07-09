package tree;

import java.util.HashMap;

/**
 * @author: renjie
 * @description: 二叉树的最大搜索拓扑结构节点数
 * @date: 2022/7/9 19:45
 */
public class MaxBinarySearchTopology {

    public static class TopologyRecord {
        public int leftValue;
        public int rightValue;

        public TopologyRecord(int leftValue, int rightValue) {
            this.leftValue = leftValue;
            this.rightValue = rightValue;
        }
    }

    public static int getMaxTopologyNodes(TreeNode head) {
        HashMap<TreeNode, TopologyRecord> recordMap = new HashMap<>();
        return process(head, recordMap);
    }

    public static int process(TreeNode current, HashMap<TreeNode, TopologyRecord> recordMap) {

        if (current == null) {
            return 0;
        }
        int left = process(current.leftChild, recordMap);
        int right = process(current.rightChild, recordMap);
        //根据当前节点的value值去更新recordMap中不满足搜索树结构的节点以及拓扑贡献记录
        modifyRecordMap(current.rightChild, current.value, recordMap, true);
        modifyRecordMap(current.leftChild, current.value, recordMap, false);
        //左右孩子对当前节点的拓扑结构贡献值
        TopologyRecord leftProvide = recordMap.get(current.leftChild);
        TopologyRecord rightProvide = recordMap.get(current.rightChild);
        int lv = leftProvide == null ? 0 : leftProvide.leftValue + 1 + leftProvide.rightValue;
        int rv = rightProvide == null ? 0 : rightProvide.leftValue + 1 + rightProvide.rightValue;
        recordMap.put(current, new TopologyRecord(lv, rv));
        //左、右孩子的最大拓扑结构，以及包含当前头节点的最大拓扑结构，取最大值
        return Math.max(Math.max(left, right), lv + rv + 1);
    }

    //根据当前头节点的值去更新recordMap
    public static int modifyRecordMap(TreeNode node, int value, HashMap<TreeNode, TopologyRecord> recordMap, boolean searchRight) {
        if (node == null || !recordMap.containsKey(node)) {
            return 0;
        }
        TopologyRecord record = recordMap.get(node);
        if ((!searchRight && node.value > value) || (searchRight && node.value < value)) {
            recordMap.remove(node);
            return record.rightValue + record.leftValue + 1;
        } else {
            int minus = modifyRecordMap(searchRight ? node.leftChild : node.rightChild, value, recordMap, searchRight);
            if (searchRight) {
                record.leftValue -= minus;
            } else {
                record.rightValue -= minus;
            }
            recordMap.put(node, record);
            return minus;
        }

    }

}

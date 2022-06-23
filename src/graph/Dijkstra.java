package graph;

import java.util.HashMap;

/**
 * @author renjie
 * @description 最短路径堆优化算法
 * @date 2022/6/5 12:05
 */
public class Dijkstra {

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    //改进的小根堆
    public static class NodeHeap {
        public Node[] nodes;
        public HashMap<Node, Integer> nodeIndexMap;
        public HashMap<Node, Integer> nodeDistanceMap;
        public int heapSize;

        public NodeHeap(int heapSize) {
            this.nodes = new Node[heapSize];
            this.nodeIndexMap = new HashMap<>();
            this.nodeDistanceMap = new HashMap<>();
            this.heapSize = 0;
        }

        public void swap(int index1, int index2) {
            nodeDistanceMap.put(nodes[index1], index2);
            nodeDistanceMap.put(nodes[index2], index1);
            Node temp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = temp;
        }

        //从insertIndex向上比较
        public void heapInsert(int insertIndex) {
            while (nodeDistanceMap.get(nodes[insertIndex]) < nodeDistanceMap.get(nodes[(insertIndex - 1) / 2])) {
                swap(insertIndex, (insertIndex - 1) / 2);
                insertIndex = (insertIndex - 1) / 2;
            }
        }

        //从startIndex开始向下比较
        public void heapfiy(int startIndex, int heapSize) {
            int leftChild = 2 * startIndex + 1;
            while (leftChild < heapSize) {
                int smallIndex = leftChild + 1 < heapSize && nodeDistanceMap.get(nodes[leftChild]) > nodeDistanceMap
                    .get(nodes[leftChild + 1]) ? leftChild + 1 : leftChild;
                if (nodeDistanceMap.get(smallIndex) >= nodeDistanceMap.get(startIndex)) {
                    break;
                }
                swap(smallIndex, startIndex);
                startIndex = smallIndex;
                leftChild = 2 * startIndex + 1;
            }
        }

        //弹出堆顶元素
        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], nodeDistanceMap.get(nodes[0]));
            swap(0, --heapSize);
            nodeIndexMap.put(nodes[heapSize], -1);
            nodeDistanceMap.remove(nodes[heapSize]);
            nodes[heapSize] = null;
            heapfiy(0, heapSize);
            return nodeRecord;
        }

        public boolean inHeap(Node node) {
            if (nodeIndexMap.containsKey(node) && nodeIndexMap.get(node) != -1) {
                return true;
            }
            return false;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public void addOrUpdate(Node node, int distance) {
            if (!nodeIndexMap.containsKey(node)) {
                nodes[heapSize] = node;
                nodeIndexMap.put(node, heapSize);
                nodeDistanceMap.put(node,distance);
                heapInsert(heapSize++);
            }
            if (inHeap(node)) {
                if (nodeDistanceMap.get(node) > distance) {
                    nodeDistanceMap.put(node, distance);
                    heapInsert(nodeIndexMap.get(node));
                }
            }
        }
    }

    public static HashMap<Node, Integer> getMinDistance(Node fromNode, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdate(fromNode, 0);
        HashMap<Node, Integer> res = new HashMap<>(size);
        while (!nodeHeap.isEmpty()) {
            NodeRecord minDistanceNode = nodeHeap.pop();
            Node current = minDistanceNode.node;
            int distance = minDistanceNode.distance;
            res.put(current, distance);
            for (Edge edge : current.edges) {
                nodeHeap.addOrUpdate(edge.toNode, edge.weight + distance);
            }
        }
        return res;

    }

    public static void main(String[] args) {

    }

}


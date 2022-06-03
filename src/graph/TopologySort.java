package graph;

import java.util.*;

/**
 * @author renjie
 * @description 有向无环图的拓扑排序
 * @date 2022/6/3 12:07
 */
public class TopologySort {

    public static List<Node> topologySort(Graph graph) {
        Queue<Node> zeroInQueue = new LinkedList<>();
        HashMap<Node, Integer> inMap = new HashMap<>();
        for (Node node : graph.nodeMap.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        List<Node> res = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node node = zeroInQueue.poll();
            res.add(node);
            for (Node next : node.nodes) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }

}

package graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @description 最小生成树P算法
 * @auther renjie
 * @date 2022/6/3 22:45
 */
public class Prim {

    public static Set<Edge> prim(Graph graph) {
        Set<Edge> res = new HashSet<>();
        Set<Node> nodeSet = new HashSet<>();
        Queue<Edge> queue = new PriorityQueue<>();
        for (Node node : graph.nodeMap.values()) {
            if (nodeSet.contains(node)) {
                continue;
            }
            for (Edge edge : node.edges) {
                queue.add(edge);
            }
            while (!queue.isEmpty()) {
                Edge edge = queue.poll();
                Node toNode = edge.toNode;
                if (!nodeSet.contains(toNode)) {
                    nodeSet.add(toNode);
                    for (Edge nextEdge : toNode.edges) {
                        queue.add(nextEdge);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}

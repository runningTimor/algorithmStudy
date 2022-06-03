package graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import unsort.UnionFind;

/**
 * @description 最小生成树K算法
 * @auther renjie
 * @date 2022/6/3 20:29
 */
public class Kruskal {

    public static Set<Edge> kruskal(Graph graph) {
        UnionFind.UnionFindSet unionFind = new UnionFind.UnionFindSet(graph.nodeMap.values());
        PriorityQueue<Edge> queue = new PriorityQueue(new EdgeCompartor());
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }
        Set<Edge> edgeSet = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            Node fromNode = edge.fromNode;
            Node toNode = edge.toNode;
            if (unionFind.isSameSet(fromNode, toNode)) {
                edgeSet.add(edge);
                unionFind.union(fromNode, toNode);
            }
        }
        return edgeSet;
    }

    public static class EdgeCompartor implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static void main(String[] args) {

    }
}

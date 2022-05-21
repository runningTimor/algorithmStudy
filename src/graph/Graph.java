package graph;

import java.util.*;

public class Graph {

    public HashSet<Edge> edges;
    //key为点的序号
    public HashMap<Integer, Node> nodeMap;

    public Graph() {
        edges = new HashSet<>();
        nodeMap = new HashMap<>();
    }


    //根据矩阵生成图结构(转换接口函数)
    public static Graph generateGraph(int[][] matrix) {
        Graph graph = new Graph();
        int m = matrix.length;
        for (int i = 0; i < m; i++) {
            if (!graph.nodeMap.containsKey(i)) {
                graph.nodeMap.put(i, new Node(String.valueOf((char) (i + 65)), matrix[i][0], matrix[i][1]));
            }
        }

        for (int i = 0; i < m; i++) {
            Node fromNode = graph.nodeMap.get(i);
            for (int j = 2; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    Node toNode = graph.nodeMap.get(j - 2);
                    Edge edge = new Edge(1, fromNode, toNode);
                    graph.edges.add(edge);
                    fromNode.nodes.add(toNode);
                    fromNode.edges.add(edge);
                    fromNode.out++;
                    toNode.in++;
                }
            }
        }

        return graph;
    }

    //图的宽度优先遍历(使用队列)
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList();
        Set<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            //todo 打印或处理
            System.out.println(current.value);

            for (Node toNode : current.nodes) {
                if (!set.contains(toNode)) {
                    set.add(toNode);
                    queue.add(toNode);
                }
            }
        }
    }

    //图的深度优先遍历（使用栈结构）
    public static void dfs(Node node){
        if (node==null){
            return;
        }
        Stack<Node> stack = new Stack();
        HashSet set = new HashSet();
        stack.add(node);
        set.add(node);
        //todo 打印或者处理
        System.out.println(node.value);

        while (!stack.isEmpty()){
            Node current = stack.pop();
            for (Node toNode :current.nodes){
                if (!set.contains(toNode)){
                    stack.push(current);
                    stack.push(toNode);
                    set.add(toNode);
                    //todo 打印或者处理
                    System.out.println(toNode.value);
                    break;
                }
            }

        }



    }


    public static void main(String[] args) {
        int[][] matrix = {
                {3, 2000, 0, 1, 1, 0, 0, 0, 0, 0},
                {3, 4000, 0, 0, 0, 1, 1, 0, 0, 0,},
                {2, 2500, 0, 0, 0, 1, 0, 0, 0, 0},
                {1, 1600, 0, 0, 0, 0, 1, 1, 1, 0},
                {4, 3800, 0, 0, 0, 0, 0, 0, 0, 1},
                {2, 2600, 0, 0, 0, 0, 0, 0, 0, 1},
                {4, 4000, 0, 0, 0, 0, 0, 0, 0, 1},
                {3, 3500, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        Graph graph = generateGraph(matrix);
        dfs(graph.nodeMap.get(0));

    }


}

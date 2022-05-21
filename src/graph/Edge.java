package graph;

public class Edge {
    //权重
    public int weight;
    public Node fromNode;
    public Node toNode;

    public Edge(int weight, Node fromNode, Node toNode) {
        this.weight = weight;
        this.fromNode = fromNode;
        this.toNode = toNode;
    }
}

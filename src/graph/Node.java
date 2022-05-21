package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    //点的值
    public String value;
    //入度
    public int in;
    //出度
    public int out;
    //从该点出发可达到的点集
    public List<Edge> edges;
    //从该点出发的边集
    public List<Node> nodes;

    //可选（根据具体题目扩展）
    public int cost;
    public int profit;

    public Node(String value,int cost, int profit) {
        this.value = value;
        this.cost = cost;
        this.profit = profit;
        in = 0;
        out = 0;
        edges = new ArrayList<>();
        nodes = new ArrayList<>();
    }
}

package graph;


import java.util.*;

//参加直播活动最大收益
public class MaxProfit {

    //保证花费递增收益递增
    public class party implements Comparable<party> {
        public int cost;
        public int profit;

        public party(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }

        @Override
        public int compareTo(party o) {
            return this.cost == o.cost ? o.profit - this.profit : this.cost - o.cost;
        }
    }


    public TreeSet<party> getMaxProfit(Node node) {
        TreeSet<party> ansSet = new TreeSet<>();
        if (node == null) {
            return ansSet;
        }
        //每个节点对应的选择及其收益有序队列
        HashMap<Node, PriorityQueue> hashMap = new HashMap();

        //图的宽度优先遍历
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        PriorityQueue<party> headNodeQueue = new PriorityQueue<>();
        headNodeQueue.add(new party(node.cost,node.profit));
        hashMap.put(node, headNodeQueue);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            PriorityQueue<party> currentNodeQueue = hashMap.get(current);
            for (Node nextNode : current.nodes) {
                //根据上一节点生成新节点记录
                if (hashMap.get(nextNode) == null) {
                    hashMap.put(nextNode, new PriorityQueue());
                }
                PriorityQueue<party> nextNodeQueue = hashMap.get(nextNode);
                currentNodeQueue.forEach(party -> {
                    party newParty = new party(party.cost + nextNode.cost, party.profit + nextNode.profit);
                    nextNodeQueue.add(newParty);
                });

                if (!set.contains(nextNode)) {
                    set.add(nextNode);
                    queue.add(nextNode);
                }
            }
        }
        //把所有节点的选择值汇总
        PriorityQueue<party> allQuene = new PriorityQueue<>();
        for (PriorityQueue<party> priorityQueue : hashMap.values()) {
            allQuene.addAll(priorityQueue);
        }

        party captain = allQuene.poll();
        ansSet.add(captain);
        //剔除每个队列中花费递增但收益却不增加的元素
        while (allQuene.size()!=0){
            party current = allQuene.poll();
            if (captain.cost!=current.cost&&captain.profit<current.profit){
                captain = current;
                ansSet.add(captain);
            }
        }
        return ansSet;
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
        Graph graph = Graph.generateGraph(matrix);
        MaxProfit maxProfit = new MaxProfit();
        TreeSet<party> treeSet = maxProfit.getMaxProfit(graph.nodeMap.get(7));

    }

}

package graph;

import java.util.List;

public class Graph {
    static class AdjNode {
        int val;
        AdjNode next;
        public AdjNode(int val, AdjNode next) {
            this.val = val;
            this.next = next;
        }

        public AdjNode() {

        }
    }

    static class EdgeNode {
        AdjNode node;
        int id;
    }

    private List<EdgeNode> edges;
}

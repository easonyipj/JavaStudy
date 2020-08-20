package graph;

import java.util.*;

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/clone-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph {

    private Map<Node, Node> nodeMap = new HashMap<>();

    // dfs
    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }

        if(nodeMap.containsKey(node)) {
            return nodeMap.get(node);
        }

        Node cloneNode = new Node(node.val, new ArrayList<>());
        nodeMap.put(node, cloneNode);

        for(Node adjNode : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(adjNode));
        }

        return cloneNode;
    }

    // bfs
    public Node cloneGraphBFS(Node node) {
        if(node == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        nodeMap.put(node, new Node(node.val, new ArrayList()));

        while(!queue.isEmpty()) {
            Node temp = queue.poll();
            for(Node adjNode : temp.neighbors) {
                if(!nodeMap.containsKey(adjNode)) {
                    queue.offer(adjNode);
                    nodeMap.put(adjNode, new Node(adjNode.val, new ArrayList()));
                }
                nodeMap.get(temp).neighbors.add(nodeMap.get(adjNode));
            }
        }

        return nodeMap.get(node);
    }

    public static void main(String[] args) {
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
    }
}

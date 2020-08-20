package graph;

import java.util.*;

/**
 * Description
 * Given an directed graph, a topological order of the graph nodes is defined as follow:
 *
 * For each directed edge A -> B in graph, A must before B in the order list.
 * The first node in the order can be any node in the graph with no nodes direct to it.
 *
 *
 */


class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
};

public class TopologicalSorting {

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        Map<DirectedGraphNode, Integer> indgrees = getIndgrees(graph);
        Queue<DirectedGraphNode> queue = new LinkedList<>();

        // find 0 indegree node
        findZeroIndgree(indgrees, queue);

        while(!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            result.add(node);
            // nodes neighbor indegree - 1
            for(DirectedGraphNode neighbor : node.neighbors) {
                indgrees.put(neighbor, indgrees.get(neighbor) - 1);
                if(indgrees.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    public void findZeroIndgree(Map<DirectedGraphNode, Integer> indgrees, Queue<DirectedGraphNode> queue) {
        for(Map.Entry<DirectedGraphNode, Integer> entry : indgrees.entrySet()) {
            if(entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
    }

    public Map<DirectedGraphNode, Integer> getIndgrees(ArrayList<DirectedGraphNode> graph) {

        Map<DirectedGraphNode, Integer> indgrees = new HashMap<>();
        for(DirectedGraphNode node : graph) {
            indgrees.put(node, 0);
        }

        for(DirectedGraphNode node : graph) {
            for(DirectedGraphNode neighbor : node.neighbors) {
                indgrees.put(neighbor, indgrees.get(neighbor) + 1);
            }
        }

        return indgrees;
    }


}

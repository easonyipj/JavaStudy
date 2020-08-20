package graph;

import java.util.*;

/**
 * Description
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs.
 * The org sequence is a permutation of the integers from 1 to n, with 1 \leq n \leq 10^41≤n≤10
 * ​4
 * Reconstruction means building a shortest common supersequence of the sequences
 * in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it).
 * Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
 *
 * Have you met this question in a real interview?
 * Example
 * Example 1:
 *
 * Input:org = [1,2,3], seqs = [[1,2],[1,3]]
 * Output: false
 * Explanation:
 * [1,2,3] is not the only one sequence that can be reconstructed,
 * because [1,3,2] is also a valid sequence that can be reconstructed.
 * Example 2:
 *
 * Input: org = [1,2,3], seqs = [[1,2]]
 * Output: false
 * Explanation:
 * The reconstructed sequence can only be [1,2].
 * Example 3:
 *
 * Input: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
 * Output: true
 * Explanation:
 * The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 * Example 4:
 *
 * Input:org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
 * Output:true
 *
 * https://www.lintcode.com/problem/sequence-reconstruction/description
 */
public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // generate graph
        Map<Integer, Set<Integer>> graph = generateGraph(seqs);
        // generate indegree
        Map<Integer, Integer> indgrees = generateIndgree(graph, seqs);

        // topo sort

        return false;
    }

    public Map<Integer, Set<Integer>> generateGraph(int[][] seqs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int[] seq : seqs) {
            for(int i : seq) {
                if(!graph.containsKey(i)) {
                    graph.put(i, new HashSet<>());
                }
            }
        }

        for(int[] seq : seqs) {
            for(int i = 1; i < seq.length; i++) {
                graph.get(i - 1).add(seq[i]);
            }
        }

        return graph;
    }

    public Map<Integer, Integer> generateIndgree(Map<Integer, Set<Integer>> graph, int[][] seqs) {
        Map<Integer, Integer> indegree = new HashMap<>();
        for(int[] seq : seqs) {
            for(int i : seq) {
                if(!indegree.containsKey(i)) {
                    indegree.put(i, 0);
                }
            }
        }

        for(Set<Integer> set : graph.values()) {
            for(Integer key : set) {
                indegree.put(key, indegree.get(key) + 1);
            }
        }

        return indegree;
    }
}

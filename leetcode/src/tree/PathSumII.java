package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Eason
 * 2019/12/18
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class PathSumII {

    private List<List<Integer>> res = new ArrayList<>();
    private int pathSum = 0;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> path = new ArrayList<>();
        dfs(path, root, sum);
        return res;
    }

    private void dfs(List<Integer> path, TreeNode node, int target) {
        if(node == null) {
            return;
        }

        pathSum += node.val;
        path.add((int) node.val);

        if(pathSum == target && node.right == null && node.left == null) {
            List<Integer> temp = new ArrayList<>(path);
            res.add(temp);
        }

        dfs(path, node.left, target);
        dfs(path, node.right, target);

        pathSum -= node.val;
        path.remove(path.size() - 1);
    }
}

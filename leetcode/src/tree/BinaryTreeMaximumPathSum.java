package tree;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
 * the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        [1]
 *       / \
 *      [2]  [3]
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  [20]
 *     /  \
 *    [15]  [7]
 *
 * Output: 42
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreeMaximumPathSum {
    /**
     * 保存出现过的最大路径和
     */
    private int sum = 0;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return sum;
    }

    private int dfs(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        // 比较当前最大值 与 以该节点为根节点路径最大和
        sum = Math.max(sum, node.val + left + right);

        return node.val + Math.max(left, right);
    }


}

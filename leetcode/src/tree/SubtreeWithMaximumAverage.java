package tree;

/**
 * Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
 *
 * LintCode will print the subtree which root is your return node.
 * It's guaranteed that there is only one subtree with maximum average.
 *
 * Have you met this question in a real interview?
 * Example
 * Example 1
 *
 * Input：
 * {1,-5,11,1,2,4,-2}
 * Output：11
 * Explanation:
 * The tree is look like this:
 *      1
 *    /   \
 *  -5     11
 *  / \   /  \
 * 1   2 4    -2
 * The average of subtree of 11 is 4.3333, is the maximun.
 * Example 2
 *
 * Input：
 * {1,-5,11}
 * Output：11
 * Explanation:
 *      1
 *    /   \
 *  -5     11
 * The average of subtree of 1,-5,11 is 2.333,-5,11. So the subtree of 11 is the maximun.
 * https://www.lintcode.com/problem/subtree-with-maximum-average/description
 * https://www.jiuzhang.com/solution/subtree-with-maximum-average/
 */
public class SubtreeWithMaximumAverage {

    private TreeNode resultNode = null;;
    private ResultType subtreeResult = null;;
    /**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        helper(root);
        return resultNode;
    }

    private static class ResultType {
        public int sum;
        public int size;
        public ResultType(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }

    private ResultType helper(TreeNode root) {
        if(root == null) {
            return new ResultType(0, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        ResultType rootResult = new ResultType(left.sum + right.sum + root.val, left.size + right.size + 1);

        if(resultNode == null || subtreeResult.sum * rootResult.size < rootResult.sum * subtreeResult.size) {
            resultNode = root;
            subtreeResult = rootResult;
        }

        return rootResult;

    }
}

package tree;

/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 * Example 1:
 *
 * Given the following tree [3,9,20,null,null,15,7]:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Return true.
 *
 * Example 2:
 *
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * Return false.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        // 自顶向下
//        if(root == null) {
//            return true;
//        }
//
//        int left = depth(root.left);
//        int right = depth(root.right);
//
//        return Math.abs(left - right) < 2 && isBalanced(root.left) && isBalanced(root.right);

        // 自底向上
        return bottomToTop(root) != -1;

    }

    public int bottomToTop(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int left = bottomToTop(node.left);
        if(left == -1) {
            return -1;
        }

        int right = bottomToTop(node.right);
        if(right == -1) {
            return -1;
        }

        return Math.abs(left - right) > 2 ? -1 : Math.max(left, right) + 1;
    }


    public int depth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = depth(root.left) + 1;
        int right = depth(root.right) + 1;

        return Math.max(left, right);
    }
}

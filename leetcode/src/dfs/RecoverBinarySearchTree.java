package dfs;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Example 1:
 *
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * Example 2:
 *
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * Follow up:
 *
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RecoverBinarySearchTree {
    private TreeNode pre;
    private TreeNode first;
    private TreeNode second;
    public void recoverTree(TreeNode root) {
        pre = null;
        dfs(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    /**
     * 中序遍历找到一个或两个异常结点
     */
    public void dfs(TreeNode root) {
        if(root == null) {
            return;
        }
        dfs(root.left);
        if(pre != null && pre.val > root.val) {
            if(first == null) {
                first = pre;
            }
            second = root;
        }
        pre = root;
        dfs(root.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node3.left = node1;
        node3.right = node4;
        node4.left = node2;
        new RecoverBinarySearchTree().recoverTree(node3);
    }

}

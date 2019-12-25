package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int[] preOrder;
    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preOrder = preorder;

        // 初始化map
        for(int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return dfs(0, preOrder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode dfs(int preStart, int preEnd, int inStart, int inEnd) {
        if(preStart > preEnd) {
            return null;
        }

        TreeNode node = new TreeNode(preOrder[preStart]);
        // pre在in中的相对位置
        int index = map.get(preOrder[preStart]) - inStart;
        node.left = dfs(preStart + 1, preStart + index, inStart, inStart + index - 1);
        node.right = dfs(preStart + index + 1, preEnd, inStart + index + 1, inEnd);

        return node;
    }

}

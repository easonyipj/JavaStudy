package tree;

/**
 * Eason
 * 2019/10/16
 * 
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class FlattenBinaryTreeToLinkedList {

    private TreeNode pre;

    public void flatten(TreeNode root) {
        if(root != null) {
            flatten(root.right);
            flatten(root.right);
            root.right = pre;
            root.left = null;
            pre = root;
        }
    }

}

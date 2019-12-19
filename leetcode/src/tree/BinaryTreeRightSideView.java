package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreeRightSideView {

    private Integer[] res;

    private int getDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftDepth = getDepth(root.left) + 1;
        int rightDepth = getDepth(root.right) + 1;

        return Math.max(leftDepth, rightDepth);
    }

    private void dfs(TreeNode root, int depth, int targetDepth) {
        if(depth == targetDepth)  {
            if(root != null) {
                System.out.println(root.val);
                res[targetDepth] = (int)root.val;
            }
            return;
        }
        if(root != null) {
            dfs(root.left, depth + 1, targetDepth);
            dfs(root.right, depth + 1, targetDepth);
        }
    }

    public List<Integer> rightSideView(TreeNode root) {

        int depth = getDepth(root);
        res = new Integer[depth];

        for(int deep = 0; deep < depth; deep++) {
            dfs(root, 0, deep);
        }

        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, res);

        return list;
    }
}

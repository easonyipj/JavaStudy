package dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area,
 * called the "root." Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *
 * Input: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HouseRobberIII {

    private Map<TreeNode, Integer> mem = new HashMap<>();

    public int rob(TreeNode root) {

        if(root == null) {
            return 0;
        }

        if(mem.containsKey(root)) {
            return mem.get(root);
        }

        int money = root.val;
        // 方式一 两个孩子的钱
        int methodA = rob(root.left) + rob(root.right);
        // 方式二 四个孙子的钱和自己的钱
        int methodB = money;
        if(root.left != null) {
            methodB += rob(root.left.left) + rob(root.left.right);
        }
        if(root.right != null) {
            methodB += rob(root.right.left) + rob(root.right.right);
        }

        int result = Math.max(methodA, methodB);
        mem.put(root, result);
        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

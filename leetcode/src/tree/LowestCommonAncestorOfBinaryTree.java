package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Eason
 * 2019/10/16
 *
 * GGiven a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *

 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *  
 *
 * Note:
 *
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the binary tree.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class LowestCommonAncestorOfBinaryTree {

    /**
     * solution 1
     */
    public static ArrayList<TreeNode> pList = new ArrayList<>();
    public static ArrayList<TreeNode> qList = new ArrayList<>();
    public static int flag = 0;

    /**
     * 用两个list分别记录p和q的前序遍历路径
     * 之后遍历两个list min(两个栈的长度)次，最后一个相同的则为最近公共祖先
     */
    public static TreeNode lowestCommonAncestorBySack(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> list = new ArrayList<>();

        dfs(root, p, list, 0);
        list.clear();
        flag = 0;
        dfs(root, q, list, 1);

        TreeNode result = null;
        int len = Math.min(pList.size(), qList.size());
        for(int i = 0; i < len; i++) {
            if(pList.get(i) == qList.get(i)) {
                result = pList.get(i);
            }
        }

        return result;
    }

    public static void dfs(TreeNode root, TreeNode p, ArrayList<TreeNode> s, int childFlag) {
        if(root == null || flag == 1) {
            return;
        }

        s.add(root);

        if(root == p) {
            if(childFlag == 0) {
                pList = new ArrayList<>(s);
            }else {
                qList = new ArrayList<>(s);
            }
            flag = 1;
            return;
        }

        dfs(root.left, p, s, childFlag);
        dfs(root.right, p, s, childFlag);
        s.remove(s.size() - 1);
    }


    /**
     * solution 2
     */
    public TreeNode answer;

    public TreeNode lowestCommonAncestorByRecursion(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return this.answer;
    }

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return false;
        }

        int mid = (root == p || root == q) ? 1 : 0;
        int left = dfs(root.left, p, q) ? 1 : 0;
        int right = dfs(root.right, p, q) ? 1 : 0;

        if(mid + left + right >= 2) {
            this.answer = root;
        }

        return mid + left + right > 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode('1');
        TreeNode node1 = new TreeNode('2');
        TreeNode node2 = new TreeNode('3');
        TreeNode node3 = new TreeNode('4');
        TreeNode node4 = new TreeNode('5');
        TreeNode node5 = new TreeNode('6');
        TreeNode node6 = new TreeNode('7');
        TreeNode node7 = new TreeNode('8');
        TreeNode node8 = new TreeNode('9');

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        node3.left = node7;
        node3.right = node8;

        TreeNode node = new LowestCommonAncestorOfBinaryTree().lowestCommonAncestorByRecursion(root, node3, node4);
        System.out.println(node.val);

    }
}

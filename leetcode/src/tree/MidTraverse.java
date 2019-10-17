package tree;

import java.util.Stack;

/**
 * Eason
 * 2019/10/15
 **/
public class MidTraverse {

    public static void dfs(TreeNode root) {
        if(root == null) {
            return;
        }
        dfs(root.left);
        System.out.println(root.val);
        dfs(root.right);
    }

    public static void traverseByStack(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode node = root;
        while(node != null || !s.empty()) {

            while (node != null) {
                s.push(node);
                node = node.left;
            }

            if(!s.empty()) {
                node = s.pop();
                System.out.println(node.val);
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.getSampleTree();
        System.out.println("stack:");
        traverseByStack(treeNode);
        System.out.println("dfs:");
        dfs(treeNode);
    }
}

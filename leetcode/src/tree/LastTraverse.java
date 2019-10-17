package tree;

import java.util.Stack;

/**
 * Eason
 * 2019/10/14
 **/
public class LastTraverse {

    public static void dfs(TreeNode root) {
        if(root == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        System.out.println(root.val);
    }

    public static void traverseByStack(TreeNode root) {
        Stack<TreeNode> storeStack = new Stack<>();
        Stack<TreeNode> traverseStack = new Stack<>();

        TreeNode node = root;

        while(node != null || !traverseStack.empty()) {

            while(node != null) {
                storeStack.push(node);
                traverseStack.push(node);
                node = node.right;
            }

            if(!traverseStack.empty()) {
                node = traverseStack.pop();
                node = node.left;
            }

        }

        while(!storeStack.empty()) {
            System.out.println(storeStack.pop().val);
        }


    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.getSampleTree();
        traverseByStack(treeNode);
        dfs(treeNode);
    }
}

package tree;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Eason
 * 2019/10/15
 **/
public class LevelTraverse {

    /**
     * traverseByDFS
     */
    public static void traverseByDFS(TreeNode root) {
        int depth = getDepth(root);
        for(int i = 1; i <= depth; i++) {
            dfs(root, 1, i);
        }
    }

    public static int getDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftDepth = getDepth(root.left) + 1;
        int rightDepth = getDepth(root.right) + 1;

        return Math.max(leftDepth, rightDepth);
    }

    public static void dfs(TreeNode root, int depth, int targetDepth) {
        if(depth == targetDepth)  {
            if(root != null) {
                System.out.println(root.val);
            }
            return;
        }
        dfs(root.left, depth + 1, targetDepth);
        dfs(root.right, depth + 1, targetDepth);
    }

    /**
     * traverseByQueue
     */
    public static void traverseByQueue(TreeNode root) {
        LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();
        TreeNode node = root;
        queue.offer(node);
        while(!queue.isEmpty()) {
            node = queue.poll();
            System.out.println(node.val);
            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.getSampleTree();
        System.out.println("stack:");
        traverseByQueue(treeNode);
        System.out.println("dfs:");
        traverseByDFS(treeNode);
    }
}

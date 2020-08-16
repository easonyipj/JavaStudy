package tree;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection link
 * to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction
 * on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string
 * can be deserialized to the original tree structure.
 *
 * Example: 
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative
 * and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize
 * and deserialize algorithms should be stateless.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SerializeBinaryTree {
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serializeByDFS(root, "");
    }

    // dfs
    public String serializeByDFS(TreeNode root, String s) {
        if(root == null) {
            return s += "None,";
        }else {
            s += root.val + ",";
            s = serializeByDFS(root.left, s);
            s = serializeByDFS(root.right, s);
            return s;
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> list = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeByDFS(list);
    }

    public TreeNode deserializeByDFS(List<String> list) {
        if(list.get(0).equals("None")) {
            list.remove(0);
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        node.left = deserializeByDFS(list);
        node.right = deserializeByDFS(list);
        return node;
    }

    // bfs
    public String serializeByBFS(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node == null) {
                sb.append("null").append(",");
            }else {
                sb.append(root.val).append(",");
                queue.offer(root.left);
                queue.offer(root.right);
            }
        }
        return sb.toString();
    }

    public TreeNode deserializeByBFS(String data) {
        List<String> list = new ArrayList<>(Arrays.asList(data.split(",")));
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = getNode(list.get(0));
        TreeNode parent = root;
        boolean isLeft = true;
        for(int i = 1; i < list.size(); i++) {
            TreeNode node = getNode(list.get(i));
            if(isLeft) {
                parent.left = node;
            }else {
                parent.right = node;
            }

            if(node != null) {
                queue.offer(node);
            }
            isLeft = !isLeft;
            if(!isLeft) {
                parent = queue.poll();
            }
        }
        return root;
    }

    private TreeNode getNode(String val){
        if(val.equals("null")){
            return null;
        }
        return new TreeNode(Integer.parseInt(val));
    }
}

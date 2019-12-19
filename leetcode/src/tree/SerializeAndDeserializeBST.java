package tree;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on
 * how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree
 * can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SerializeAndDeserializeBST {

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }

        public TreeNode() {

        }
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }

    private void dfs(TreeNode node, StringBuilder sb) {
        if(node == null) {
            return;
        }

        sb.append(String.valueOf(node.val)).append("#");
        dfs(node.left, sb);
        dfs(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split("#");
        TreeNode root = null;
        if(!"".equals(nodes[0])) {
            root = new TreeNode(Integer.parseInt(nodes[0]));
        }
        for(int i = 1; i < nodes.length; i++) {
            if(!"".equals(nodes[i])) {
                TreeNode node = new TreeNode(Integer.parseInt(nodes[i]));
                assert root != null;
                insert(root, node);
            }
        }
        return root;
    }

    private void insert(TreeNode root, TreeNode node) {
        if(node.val < root.val) {
            if(root.left != null) {
                insert(root.left, node);
            }else {
                root.left = node;
            }
        }else {
            if(root.right != null) {
                insert(root.right, node);
            }else {
                root.right = node;
            }
        }
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2);

        node.left = node1;
        node.right = node2;
        node1.right = node3;

        String text = new SerializeAndDeserializeBST().serialize(node);
        System.out.println(text);

        new SerializeAndDeserializeBST().deserialize(text);

    }
}

package trees.minDepth;

import trees.Node;

public class RecursiveMinDepth implements MinDepth{
    private int minDepth = Integer.MAX_VALUE;

    public int calculate(Node root) {
        if (root == null) return 0;
        minDepth(root, 1);
        return minDepth;
    }

    private void minDepth(Node node, int depth) {
        if (node == null) {
            return;
        }

        var isLeaf = node.left == null && node.right == null;

        if (isLeaf) {
            minDepth = Math.min(minDepth, depth);
            return;
        }

        minDepth(node.left, depth + 1);
        minDepth(node.right, depth + 1);
    }

}

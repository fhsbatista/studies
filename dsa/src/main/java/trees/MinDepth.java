package trees;

public class MinDepth {
    private int minDepth = Integer.MAX_VALUE;
    private Node root;

    public MinDepth(Node root) {
        this.root = root;
    }

    public int calculate() {
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

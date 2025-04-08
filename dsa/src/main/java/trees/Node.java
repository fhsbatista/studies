package trees;

public class Node {
    private int value;
    private Node left;
    private Node right;

    private Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public static Node of(int value, Node left, Node right) {
        return new Node(value, left, right);
    }

    public boolean isValid() {
        return validate(this, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validate(Node node, int min, int max) {
        if (node == null) return true;

        var valid = node.value > min && node.value < max;
        if (!valid) return false;

        var left = validate(node.left, min, node.value);
        var right = validate(node.right, node.value, max);

        return left && right;
    }
} 
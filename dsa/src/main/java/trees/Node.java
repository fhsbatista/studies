package trees;

public class Node {
    public int value;
    public Node left;
    public Node right;

    private Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public static Node of(int value, Node left, Node right) {
        return new Node(value, left, right);
    }

    public static Node of(int value) {
        return new Node(value, null, null);
    }
} 
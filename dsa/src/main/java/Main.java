import trees.Node;

public class Main {
    public static void main(String[] args) {
        Node root = Node.of(
                40,
                Node.of(20, null, null),
                Node.of(60,
                        Node.of(70, null, null),
                        null
                )
        );

        System.out.println(root.isValid());
    }
} 
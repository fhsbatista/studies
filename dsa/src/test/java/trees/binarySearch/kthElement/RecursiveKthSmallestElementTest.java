package trees.binarySearch.kthElement;

import org.junit.jupiter.api.Test;
import trees.Node;
import trees.binarySearch.kthElement.RecursiveKthSmallestElement;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveKthSmallestElementTest {
    @Test
    void testFind() {
        Node root = Node.of(
                10,
                Node.of(
                        8,
                        Node.of(7, Node.of(2), null),
                        Node.of(9)
                ),
                Node.of(
                        15,
                        Node.of(12, Node.of(11), null),
                        Node.of(17, null, Node.of(30))
                )
        );
        assertEquals(17, new RecursiveKthSmallestElement().find(root, 9));
    }
}
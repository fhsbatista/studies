package trees.binarySearch.minDepth;

import org.junit.jupiter.api.Test;
import trees.Node;
import trees.minDepth.RecursiveMinDepth;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveMinDepthTest {
    @Test
    void testMinDepth() {
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
        assertEquals(3, new RecursiveMinDepth().calculate(root));
    }

    @Test
    void testNullTree() {
        Node root = null;
        assertEquals(0, new RecursiveMinDepth().calculate(root));
    }

    @Test
    void testRootWithNoNodes() {
        Node root = Node.of(10);
        assertEquals(1, new RecursiveMinDepth().calculate(root));
    }
}
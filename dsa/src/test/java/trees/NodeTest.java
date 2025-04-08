package trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testValidBinarySearchTree() {
        Node root = Node.of(
                40,
                Node.of(20, null, null),
                Node.of(60, null, null)
        );
        assertTrue(root.isValid());
    }

    @Test
    void testInvalidBinarySearchTree() {
        Node root = Node.of(
                40,
                Node.of(60, null, null),
                Node.of(20, null, null)
        );
        assertFalse(root.isValid());
    }

    @Test
    void testEmptyTree() {
        Node root = null;
        assertTrue(root == null || root.isValid());
    }

    @Test
    void testSingleNode() {
        Node root = Node.of(40, null, null);
        assertTrue(root.isValid());
    }
} 
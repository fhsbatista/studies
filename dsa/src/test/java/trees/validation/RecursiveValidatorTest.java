package trees.validation;

import org.junit.jupiter.api.Test;
import trees.Node;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveValidatorTest {

    @Test
    void testValidBinarySearchTree() {
        Node root = Node.of(
                40,
                Node.of(20, null, null),
                Node.of(60, null, null)
        );
        assertTrue(new RecursiveValidator().isValid(root));
    }

    @Test
    void testInvalidBinarySearchTree() {
        Node root = Node.of(
                40,
                Node.of(60, null, null),
                Node.of(20, null, null)
        );
        assertFalse(new RecursiveValidator().isValid(root));
    }

    @Test
    void testEmptyTree() {
        Node root = null;
        assertTrue(new RecursiveValidator().isValid(root));
    }

    @Test
    void testSingleNode() {
        Node root = Node.of(40, null, null);
        assertTrue(new RecursiveValidator().isValid(root));
    }
}
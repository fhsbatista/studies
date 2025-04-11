package trees.binarySearch.validation;

import trees.Node;

public class RecursiveValidator implements BinaryTreeValidator{
    @Override
    public boolean isValid(Node node) {
        return validate(node);
    }

    private boolean validate(Node node) {
        if (node == null) return true;

        if (!isNodeValid(node)) {
            return false;
        }

        var isLeftValid = validate(node.left);
        var isRightValid = validate(node.right);

        return isLeftValid && isRightValid;
    }

    private boolean isNodeValid(Node node) {
        var isLeftValid = node.left == null || node.left.value < node.value;
        var isRightValid = node.right == null || node.right.value > node.value;

        return isLeftValid && isRightValid;
    }

}

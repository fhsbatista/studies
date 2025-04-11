package trees.binarySearch.kthElement;

import trees.Node;

import java.util.ArrayList;
import java.util.List;

public class RecursiveKthSmallestElement implements KthSmallestElement {
    @Override
    public int find(Node root, int kth) {
        List<Integer> smallests = new ArrayList<>();

        populateFromSmallestToKth(root, kth, smallests);

        return smallests.get(kth - 1);
    }

    private void populateFromSmallestToKth(Node node, int kth, List<Integer> elements) {
        if (elements.size() >= kth) return;
        if (node == null) return;

        //in order
        populateFromSmallestToKth(node.left, kth, elements);
        elements.add(node.value);
        populateFromSmallestToKth(node.right, kth, elements);
    }

}

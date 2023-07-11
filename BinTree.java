

public class BinTree {
    Node root;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            root.color = Color.black;
            return true;
        }
        if (root != null){                          //add if
            boolean result = addNode(root,value) != null;
            root = rebalance(root);
            root.color = Color.black;
            return result;
        }
        
        if (addNode(root, value) != null)
            return true;
        return false;
    }

    private Node addNode(Node node, int value) {
        if (node.value == value)
            return null;
        if (node.value > value) {
            if (node.left == null) {
                node.left = new Node(value);
                node.left = rebalance(node.left); // add str
                return node.left;
            } else
                return addNode(node.left, value);
        } else  {
            if (node.right == null) {
                node.right = new Node(value);
                node.right = rebalance(node.right); // add str
                return node.right;
            } else
                return addNode(node.right, value);
        }
    }

    public boolean contain(int value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value == value)
                return true;
            if (currentNode.value > value)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        return false;
    }



    private class Node {
        int value;
        Node left;
        Node right;
        Color color;
        Node() {
            this.color = Color.red;
        }

        Node(int _value) {
            this.value = _value;
            this.color = Color.red;
        }
    }
    enum Color {red, black}


    private Node rebalance(Node node){
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.right != null && result.right.color == Color.red &&
                (result.left == null || result.left.color == Color.black)) {
                    needRebalance = true;
                    result = rightSwap(result);
                }
            if (result.left != null && result.left.color == Color.red &&
                (result.left.left == null || result.left.left.color == Color.red)) {
                    needRebalance = true;
                    result = leftSwap(result);
                }
            if (result.left != null && result.left.color == Color.red &&
                (result.right != null || result.right.color == Color.red)) {
                    needRebalance = true;
                    colorSwap(result);
                }
            }
            while (needRebalance);
            return result;
        }

    private Node rightSwap(Node node) {
        Node right = node.right;
        Node between = right.left;
        right.left = node;
        node.right = between;
        right.color = node.color;
        node.color = Color.red;
        return right;
    }

    private Node leftSwap(Node node) {
        Node left = node.left;
        Node between = left.right;
        left.right = node;
        node.left = between;
        left.color = node.color;
        node.color = Color.red;
        return left;
    }

    private void colorSwap(Node node) {
        node.right.color = Color.black;
        node.left.color = Color.red;
        node.color = Color.red;
    }
}
package SymbolTables;

public class TwoThreeTree {
    private Node root;

    public TwoThreeTree() {
        root = null;
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            Node newRoot = insert(root, key);
            if (newRoot != null) {
                root = newRoot;
            }
        }
    }

    private Node insert(Node node, int key) {
        if (node.isLeaf()) {
            if (!node.isFull()) {
                node.insertKey(key);
                return null;
            } else {
                return split(node, key);
            }
        } else {
            Node temp = null;
            if (key < node.keys[0]) {
                temp = insert(node.children[0], key);
                if (temp != null) {
                    return handleParentInsert(node, temp.keys[0], temp);
                }
            } else if (node.numKeys == 1 || key < node.keys[1]) {
                temp = insert(node.children[1], key);
                if (temp != null) {
                    return handleParentInsert(node, temp.keys[0], temp);
                }
            } else {
                temp = insert(node.children[2], key);
                if (temp != null) {
                    return handleParentInsert(node, temp.keys[0], temp);
                }
            }
            return null;
        }
    }

    private Node handleParentInsert(Node parent, int key, Node child) {
        if (!parent.isFull()) {
            if (key < parent.keys[0]) {
                parent.children[2] = parent.children[1];
                parent.children[1] = parent.children[0];
                parent.children[0] = child;
                parent.keys[1] = parent.keys[0];
                parent.keys[0] = key;
            } else {
                parent.children[2] = parent.children[1];
                parent.children[1] = child;
                parent.keys[1] = key;
            }
            parent.numKeys++;
            return null;
        } else {
            return split(parent, key);
        }
    }

    private Node split(Node node, int key) {
        int midKey;
        Node leftChild, rightChild;

        if (key < node.keys[0]) {
            midKey = node.keys[0];
            leftChild = new Node(key);
            rightChild = new Node(node.keys[1]);
        } else if (key < node.keys[1]) {
            midKey = key;
            leftChild = new Node(node.keys[0]);
            rightChild = new Node(node.keys[1]);
        } else {
            midKey = node.keys[1];
            leftChild = new Node(node.keys[0]);
            rightChild = new Node(key);
        }

        Node newNode = new Node(midKey);
        newNode.children[0] = leftChild;
        newNode.children[1] = rightChild;
        return newNode;
    }

    public void print() {
        print(root, "", true);
    }

    private void print(Node node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.keys[0] + (node.numKeys == 2 ? ", " + node.keys[1] : ""));
            for (int i = 0; i < node.numKeys + 1; i++) {
                print(node.children[i], prefix + (isTail ? "    " : "│   "), i == node.numKeys);
            }
        }
    }

    public static void main(String[] args) {
        TwoThreeTree tree = new TwoThreeTree();
        int[] keys = {5, 15, 25, 35, 45, 55, 65, 75};
        for (int key : keys) {
            tree.insert(key);
        }
        tree.print();
    }
}

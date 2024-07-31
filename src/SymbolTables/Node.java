package SymbolTables;

class Node {
    int[] keys;
    Node[] children;
    int numKeys;

    public Node(int key) {
        keys = new int[2]; // max 2 keys
        children = new Node[3]; // max 3 children
        keys[0] = key;
        numKeys = 1;
    }

    public boolean isLeaf() {
        return children[0] == null;
    }

    public boolean isFull() {
        return numKeys == 2;
    }

    public void insertKey(int key) {
        if (keys[0] > key) {
            keys[1] = keys[0];
            keys[0] = key;
        } else {
            keys[1] = key;
        }
        numKeys++;
    }
}


package com.company.bst;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private int count;
    private Node memo;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right, pred, succ;
        private int N;


        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    private int size(Node node) {
        return node == null ? 0 : node.N;
    }

    private Value get(Node node, Key key) {
        count++;
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            memo = node;
            return node.val;
        }
    }

    private Node getNode(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getNode(node.left, key);
        } else if (cmp > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    private Node put(Node node, Key key, Value val) {
      if (node == null) {
          Node newNode = new Node(key, val, 1);
          newNode.succ = ceil(root, key);
          newNode.pred = floor(root, key);
          return newNode;
      }
      int cmp = key.compareTo(node.key);
      if (cmp < 0) node.left = put(node.left, key, val);
      else if (cmp > 0) node.right = put(node.right, key, val);
      else node.val = val;
      node.N = 1 + size(node.left) + size(node.right);
      return node;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        else return min(node.left);
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            Node t = floor(node.right, key);
            return t == null ? node : t;
        } else if (cmp < 0) {
            return floor(node.left, key);
        } else return node;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return ceil(node.right, key);
        } else if (cmp < 0) {
            Node t = ceil(node.left, key);
            return t == null ? node : t;
        } else return node;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        else return max(node.right);
    }

    private Node select(Node node, int k) {
        if (node == null) return null;
        int t = size(node.left);
        if (t > k) {
            return select(node.left, k);
        } else if (t < k) {
            return select(node.right, k - t - 1);
        } else return node;
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return rank(node.left, key);
        } else if (cmp > 0) {
            return 1 + size(node.left) + rank(node.right, key);
        } else return size(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        else return deleteMin(node.left);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) return node.left;
        else return deleteMax(node.right);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
            return node;
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
            return node;
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node newNode = min(node);
            newNode.left = node.left;
            return newNode;
        }
    }

    private void print(Node node) {
        if (node == null) return;
        print(node.left);
        System.out.print(" " + node.key.toString() + " ");
        print(node.right);
    }

    private boolean isBinaryTree(Node node) {
        if (node == null) return true;
        int c = count(node);
        System.out.println("key:" + node.key + " N: " + node.N + " C:" + c + " ok:" + String.valueOf(node.N == c));

        return node.N == c;
    }

    private boolean isBinaryTreeStrictly(Node node) {
        if (node == null) return true;
        if (!isBinaryTreeStrictly(node.left)) return false;
        else if (!isBinaryTreeStrictly(node.right)) return false;
        else return isBinaryTree(node);
    }

    private int count(Node node) {
        if (node == null) return 0;
        else return 1 + count(node.left) + count(node.right);
    }



    public int size() {
        return size(root);
    }

    public Value get(Key key) {
        Value val;
        count = 0;

        if (memo != null && key.compareTo(memo.key) == 0) {
            val = get(memo, key);
        } else {
            val = get(root, key);
        }
        System.out.println("共查找" + count + "次");
        count = 0;
        return val;
    }


    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Key min() {
        Node node = min(root);
        if (node == null) return null;
        else return min(root).key;
    }

    public Key max() {
        Node node = max(root);
        if (node == null) return null;
        else return max(root).key;
    }

    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) return null;
        return node.key;
    }

    public Key ceil(Key key) {
        Node node = ceil(root, key);
        if (node == null) return null;
        return node.key;
    }

    public Key select(int k) {
        Node node = select(root, k);
        if (node == null) return null;
        else return node.key;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public void print() {
        print(root);
    }

    public boolean isBinaryTree() {
        return isBinaryTree(root);
    }

    public boolean isBinaryTreeStrictly() {
        return isBinaryTreeStrictly(root);
    }

    public Key prev(Key key) {
        Node node = getNode(root, key);
        return node == null || node.pred == null ? null : node.pred.key;
    }

    public Key next(Key key) {
        Node node = getNode(root, key);
        return node == null || node.succ == null ? null : node.succ.key;
    }

}

package com.company.red_black_tree;

import java.util.TreeMap;

public class RBT<Key extends Comparable<Key>, Value> {
    private Node root;

    private enum Color {
        Red, Black
    }

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        Color color;

        Node(Key key, Value val, int N, Color color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }

        public boolean isRed() {
            return this.color == Color.Red;
        }
    }

    private int size(Node x) {
        return x == null ? 0 : x.N;
    }

    private Node rotateLeft(Node h) {
       Node x = h.right;
       h.right = x.left;
       x.left = h;
       x.color = h.color;
       h.color = Color.Red;
       h.N = 1 + size(h.left) + size(h.right);
       return x;
    }

    private Node min(Node x) {
        if (x == null) return null;
        if (x.left == null) return x;
        return min(x.left);
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = Color.Red;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == Color.Red;
    }

    private void flipColors(Node x) {
        x.color = Color.Red;
        x.left.color = Color.Black;
        x.right.color = Color.Black;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1, Color.Red);
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    private void moveFlipColors(Node x) {
        x.color = Color.Black;
        if (x.left != null) x.left.color = Color.Red;
        if (x.right != null) x.right.color = Color.Red;
    }

    private Node moveRedLeft(Node x) {
        moveFlipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            //flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        moveFlipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            moveFlipColors(x);
        }
        //x = rotateRight(x);
//        x.color = Color.Black;
        return x;
    }

    private Node balance(Node x) {
        if (isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node delMin(Node x) {
        if (x.left == null) {
            System.out.println("删除的key: " + x.key + "，删除的值: " + x.val);
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = delMin(x.left);
        return balance(x);
    }


    private Node delMax(Node x) {
        if (isRed(x.left)) {
            x = rotateRight(x);
        }
        if (x.right == null) {
            System.out.println("删除的key: " + x.key + "，删除的值: " + x.val);
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x);
        }

        x.right = delMax(x.right);
        return balance(x);
    }

    private Node del(Node x, Key key) {
        if (key.compareTo(x.key) < 0) {
            if (x.left == null) {
                System.out.println("未找到需要删除的元素");
                return balance(x);
            }
            if(!isRed(x.left) && !isRed(x.left.left)){
                x = moveRedLeft(x);
            }

            x.left = del(x.left, key);
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
            }

            if (x.right == null) {

                if (key.compareTo(x.key) != 0) {
                    System.out.println("未找到需要删除的元素");
                    return balance(x);
                }

                if (key.compareTo(x.key) == 0 && x.right == null) {
                    System.out.println("找到需要删除的元素 -> key: " + x.key + ", value: " + x.val);
                    return null;
                }
            } else {
                if (!isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (key.compareTo(x.key) == 0) {
                    System.out.println("找到需要删除的元素 -> key: " + x.key + ", value: " + x.val);
                    Node minX = min(x.right);
                    System.out.println("用来替代的最小元素: " + minX.key + ':' + minX.val);
                    x.key = minX.key;
                    x.val = minX.val;
                    x.right = delMin(x.right);
                } else {
                    x.right = del(x.right, key);
                }
            }
//
//            if(key.compareTo(x.key) != 0 && x.right == null){    // 我们没有找到目标键，我们将其删除
//                System.out.println("未找到需要删除的元素");
//                return balance(x);
//            }
//            if (key.compareTo(x.key) == 0 && (x.right == null)) {
//                System.out.println("找到需要删除的元素 -> key: " + x.key + ", value: " + x.val);
//                return null;
//            }


//            if (!isRed(x.right) && !isRed(x.right.left)) {
//                x = moveRedRight(x);
//            }
//            if (key.compareTo(x.key) == 0) {
//                System.out.println("找到需要删除的元素 -> key: " + x.key + ", value: " + x.val);
//                Node minX = min(x.right);
//                if (minX == null) return null;
//                System.out.println("用来替代的最小元素: " + minX.key + ':' + minX.val);
//                x.key = minX.key;
//                x.val = minX.val;
//                x.right = delMin(x.right);
//            } else {
//                x.right = del(x.right, key);
//            }

        }
        return balance(x);
    }

    public boolean isEmpty() {
        boolean isEmpty = root == null? true : false;
        return isEmpty;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = Color.Black;
        return;
    }

    public void delMin() {
        if (isEmpty()) return;
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = Color.Red;
        }
        root = delMin(root);
        if (!isEmpty()) root.color = Color.Black;
    }

    public void delMax() {

        if (isEmpty()) return;
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = Color.Red;
        }
        root = delMax(root);
        if (!isEmpty()) root.color = Color.Black;
    }

    public void del(Key key) {
        System.out.println("尝试删除key ->" + key);
        if (isEmpty()) {
            System.out.println("树为空, 直接结束此次调用\n");
            return;
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = Color.Red;
        }
        root = del(root, key);
        if (!isEmpty()) root.color = Color.Red;
        System.out.println();

    }


    public Value getMin() {
        return min(root).val;
    }
}

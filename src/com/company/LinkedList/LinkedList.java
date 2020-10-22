package com.company.LinkedList;

public class LinkedList<Key, Value> {
    public Node first;
    public class Node {
        public Key key;
        public Value val;
        public Node next;

        Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }

        @Override
        public String toString() {
            return "[" + this.key.toString() + "," + this.val.toString() + "]";
        }
    }

    public LinkedList() {
        this.first = null;
    }

    public Node getFirst() {
        return this.first;
    }

    public Value get(Key key) {
        Node node = this.first;
        while (node != null && node.key != key) {
            node = node.next;
        }
        if (node == null) return null;
        return node.val;
    }

    public void put(Key key, Value val) {
        if (first == null) {
            first = new Node(key, val);
            return;
        }

        Node node = first;
        while (node.key != key && node.next != null) {
            node = node.next;
        }
        if (node.key == key) node.val = val;
        else node.next = new Node(key, val);
//        System.out.println(this.toString());
    }

    public void delete(Key key) {
        Node node = first;
        Node preNode = null;
        while (node != null) {
            if (node.key == key) {
                if (preNode != null) preNode.next = node.next;
                else this.first = node.next;
                break;
            }
            preNode = node;
            node = node.next;
        }
//        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String str = "";
        Node node = first;

        while (node != null) {
            if (!str.isEmpty()) str = str + "->";
            str = str + node.toString();
            node = node.next;
        }
        return str;
    }

    public boolean isEmpty() {
        return this.first == null;
    }
}

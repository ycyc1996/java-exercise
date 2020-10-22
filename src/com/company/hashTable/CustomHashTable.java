package com.company.hashTable;



import com.company.LinkedList.LinkedList;

public class CustomHashTable<K, V> {

    static int DEFAULT_SIZE = 8;
    LinkedList<K, V>[] head_arr;
    int N = 0;
    int size = 0;
    double loadFactor = 0.75;

    private class Node<K, V> {
        K key;
        V val;
        Node next;
        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }



    public int hash(K key) {
        return (int)(key.hashCode() & (this.head_arr.length - 1));
    }

    private void resize(int size) {
        LinkedList<K, V>[] old_node_arr = this.head_arr;
        LinkedList<K, V>[] next_node_arr = new LinkedList[size];
        this.head_arr = next_node_arr;
        this.size = size;
        if (old_node_arr != null) {
            for (int i = 0; i < old_node_arr.length; i++) {
                LinkedList<K, V> head = old_node_arr[i];
                if (head != null) {
                    while (!head.isEmpty()) {
                        K key = head.getFirst().key;
                        V val = head.getFirst().val;
                        head.delete(key);
                        this.put(key, val);
                    }
                }
            }
        }
    }

    public CustomHashTable(int size) {
        this.resize(size);
    }

    public CustomHashTable() {
        this(CustomHashTable.DEFAULT_SIZE);
    }

//    private void put(LinkedList<K, V> head) {
//        if (head == null || head.isEmpty()) return;
//        int hashCode = this.hash(head.getFirst().key);
//        this.head_arr[hashCode] = head;
//    }

    public void put(K key, V val) {
        if (this.head_arr[hash(key)] == null) {
            this.head_arr[hash(key)] = new LinkedList<K, V>();
        }
        LinkedList<K, V> head = this.head_arr[hash(key)];
        head.put(key, val);
        this.N += 1;

        if (this.loadFactor() > 0.5) {
            System.out.println("槽位占用" + this.factorSlot() + " 装载因子 -> " + this.loadFactor() + " 扩容至" + 2 * this.head_arr.length);
            resize(2 * this.head_arr.length);
        }
    }

    public void delete(K key) {
        LinkedList<K, V> head = this.head_arr[hash(key)];
        if (head != null) {
            head.delete(key);
            this.N -= 1;
        }
    }

    public V get(K key) {
        int hashCode = hash(key);
        LinkedList<K, V> head = this.head_arr[hashCode];
        if (head == null) return null;
        return head.get(key);
    }

    public int size() {
        return this.N;
    }

    public float loadFactor() {
        return (float)this.factorSlot() / this.head_arr.length;
    }

    public int factorSlot() {
        int n = 0;
        for (int i = 0; i < this.head_arr.length; i++) {
            if (this.head_arr[i] != null && !this.head_arr[i].isEmpty()) n++;
        }
        return n;
    }
}

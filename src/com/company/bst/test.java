package com.company.bst;

public class test {

    public static class INT implements Comparable<INT>{
        int val;
        public INT(int i) {
            this.val = i;
        }

        @Override
        public int compareTo(INT o) {
            return this.val - o.val;
        }

        @Override
        public String toString() {
            return "" + this.val;
        }

    }

    public static void main(String[] args) {
        BST<INT, INT> tree = new BST<>();
        int[] arr = { 5, 2, 6, 1, 3, 7, 4, 0, -1 };
        for (int i = 0; i < arr.length; i++) {
            INT key = new INT(arr[i]);
            INT val = new INT(arr[i] + 1);
            tree.put(key, val);
        }

        tree.print();
        System.out.println();
        // 测试缓存
        INT key = new INT(4);
        System.out.println(tree.get(key));
        System.out.println(tree.get(key));
        tree.put(key, new INT(99));
        System.out.println(tree.get(key));
        // 验证是否有环
        System.out.println("isBinaryTree: " + tree.isBinaryTree());
        System.out.println("isBinaryTreeStrictly: " + tree.isBinaryTreeStrictly());
        // 有序性检查
        System.out.println(tree.select(1));
        for (int i = 0; i < arr.length; i++) {
            INT key1 = new INT(arr[i]);
            int k = tree.rank(key1);
            System.out.println("key: " + key1 + " rank:" + k + " select: " + tree.select(k));
        }
        // next prev
        System.out.println("pre: " + tree.prev(key) + " next: " + tree.next(key));
    }
}



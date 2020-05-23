package com.company.red_black_tree;

public class test {
    public static void main(String[] args) {
        int[] arr = { 5, 2, 6, 1, 3, 7, 4, 0, -1 };
//        int[] arr = { 5, 2, 6, 1, 3,  };

        RBT t = new RBT<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            Integer key = new Integer(arr[i]);
            System.out.print(" " + key + " ");
            t.put(key, key );
        }
        System.out.println();
//        t.delMax();
////        t.delMax();
////
////        t.delMax();
////        t.delMax();
////        t.delMax();
////        t.delMax();
////        t.delMax();
////        t.delMax();
////        t.delMax();

        System.out.println(t.getMin());
        t.del(100);
        t.del(2);
        t.del(6);
        t.del(0);
        t.del(-2);
        t.del(5);
        t.del(-1);
        t.del(7);
        t.del(4);
        t.del(7);
        t.del(3);
        t.del(1);
        t.del(1);
        t.del(6);
        t.del(0);
        t.del(-2);
        t.del(5);
        t.del(-1);
        t.del(7);
        t.del(4);









//        t.delMin();
//        t.delMin();
//        t.delMin();
//        t.delMin();
//        t.delMin();
//        t.delMin();
//        t.delMin();
//        t.delMin();
//        t.delMin();
//        t.delMin();
//        t.delMin();
//        t.delMin();

    }
}

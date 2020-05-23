package com.company.min_heap;

public class test {
    public static void main(String[] args) {
        // write your code here
        int arr[] = {  10, 7, 2, 5, 1, 16, 8, 3, 9, 11, 22, 33, 10, 2, -1 };
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        min_heap h = new min_heap(arr, 20);
        System.out.println();
        for (int i = 0; i < h.getSize(); i++) {
            System.out.print(h.getHeap()[i] + " ");
        }
        System.out.println();
        while (h.getSize() > 0) {
            System.out.print(h.pop() +  " ");
        }
    }
}

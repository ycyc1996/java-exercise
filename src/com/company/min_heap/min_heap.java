package com.company.min_heap;

public class min_heap {
    private int[] heap;
    private int N = 0;

    public min_heap(int[] arr, int Max) {
        heap = new int[Max];
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }

    }

    public int insert(int n) {
        heap[N] = n;
        N++;
        swim(N - 1);
        return n;
    }

    public int pop() {
        int min = heap[0];
        exch(0, N - 1);
        heap[N - 1] = Integer.MAX_VALUE;
        sink(0);
        N--;
        return min;
    }


    public int[] getHeap() {
        return heap;
    }



    public int getSize() {
        return N;
    }

    private void exch(int i, int j) {
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
        return;
    }

    private boolean less(int i, int j) {
        return heap[i] < heap[j];
    }

    private void swim(int k) {
        while (k > 0 && this.less(k, (k - 1) / 2)) {
            exch(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    private void sink(int k) {
        while (2 * k + 1 <= N - 1) {
            int s = 2 * k + 1;
            if (s < N - 1 && less(s + 1, s)) s++;
            if (less(k, s)) break;
            else {
                exch(k, s);
                k = s;
            }
        }
    }
}

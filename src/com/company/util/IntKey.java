package com.company.util;

public class IntKey implements Comparable<IntKey> {
    int val;
    public IntKey(int i) {
        this.val = i;
    }

    @Override
    public int compareTo(IntKey o) {
        return this.val - o.val;
    }

    @Override
    public String toString() {
        return "" + this.val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

}

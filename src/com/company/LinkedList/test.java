package com.company.LinkedList;

public class test {
    public static void main(String[] args) {
        LinkedList list = new LinkedList<String, Integer>();
        list.put('a', 1);
        list.put('b', 2);
        list.put('c', 3);
        list.put('d', 4);
        list.put('e', 5);
        list.put('a', 11);
        list.put('c', 33);

        System.out.println("--------");
        list.delete('b');
        list.delete('e');
        list.delete('c');
        list.put('b', 2);

    }
}

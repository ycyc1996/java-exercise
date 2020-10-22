package com.company.hashTable;

import java.util.HashMap;
import java.lang.Math;

public class test {
    public static void main(String[] args) {
        CustomHashTable<String, String> h = new CustomHashTable<>();
        int size = 20;
        String[] str_list = new String[size];
        String[] res_list = new String[size];
        for (int i = 0; i < str_list.length; i++) {
//            int len = 12 + (int)(Math.random() * 1);
            str_list[i] = RandomString.getRandomString(12);
            res_list[i] = Integer.toBinaryString(str_list[i].hashCode());
        }

//        for (int i = 0; i < str_list.length; i++) {
//            int hash = str_list[i].hashCode();
//            if (hash < 0) hash = -hash;
//            System.out.println("key: " + str_list[i] + " -> hash:" + hash + " -> " + (hash));
//            String hashString = Integer.toBinaryString(hash);
//            System.out.println("key: " + str_list[i] + " -> finalHash: " + hashString + "(" + hashString.length() +") " );
//        }
//
        for (int i = 0; i < str_list.length; i++) {
            h.put(str_list[i], res_list[i]);
        }


//        for (int i = 0; i < str_list.length; i++) {
//            String key = str_list[i];
//            String val = Integer.toBinaryString(key.hashCode());
//            String res = h.get(key);
//            System.out.println("key: " + key + "val: " + val + " | search val: " + res + "equal? -> " + res.equals(val));
//        }


        boolean check = true;
        for (int i = 0; i < str_list.length; i++) {
            String key = str_list[i];
            String val = res_list[i];
            String res = h.get(key);
            boolean equal = val == null ? res == null : val.equals(res);
            check = check && equal;
            System.out.println("key: " + key + "val: " + val + " | search val: " + res + " equal? -> " + equal);
        }

        System.out.println("第一次检查结果 -> " + check);
        h.delete(str_list[3]);
        res_list[3] = null;

        h.delete(str_list[7]);
        res_list[7] = null;

        h.delete(str_list[12]);
        res_list[12] = null;


        h.delete(str_list[14]);
        res_list[14] = null;



        System.out.println("元素个数" + h.size());
        System.out.println("装载槽位个数" + h.factorSlot());

        for (int i = 0; i < res_list.length; i++) {
            System.out.println(res_list[i]);
        }

        for (int i = 0; i < str_list.length; i++) {
            String key = str_list[i];
            String val = res_list[i];
            String res = h.get(key);
            boolean equal = val == null ? res == null : val.equals(res);
            check = check && equal;
            System.out.println("key: " + key + "val: " + val + " | search val: " + res + " equal? -> " + equal);
        }

        System.out.println("第二次检查结果 -> " + check);
    }
}



package com.company.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.Timer;

public class demo {

    private static class MyTask extends TimerTask {
        private String message;
        private int count;
        private Timer t;
        MyTask(String message, Timer t) {
            this.message = message;
            this.count = 0;
            this.t = t;

        }
        @Override
        public void run() {
            if (count < 5) {
                System.out.println(new Date() + ":"+ message);
                this.count += 1;
            } else {
                t.cancel();
            }

        }
    }

    public static void main(String[] args) {
        Math math = new Math();
        System.out.println(math.e);
        System.out.println(math.PI);
        System.out.println(math.zero);

        System.out.println(-1 >>> 1);
        System.out.println(-7 >> 1);


        int a = 1221222211, b = 1111111111;

        int c = (a + b);

        System.out.println(c);




        System.out.println(10 == 10);
        System.out.println((new Integer(10)).equals(new Integer(10)) );

        String s = new String(" hello  world ! ");
        System.out.println(s.length());

        System.out.println(s.trim().length());




        int x = 256;
        System.out.println((byte)x);
        
        String str = new String("ABCC");
        switch (str.charAt(2)) {
            default:
                System.out.println("no match");
            case 'A':
                System.out.println("match A");
            case 'B':
                System.out.println("match B");
        }


        try {
            Math.printDiamond(14);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        int[] array1 = { 1, 2, 3 };
        int[] array2 = { 1, 2, 3 };


        System.out.println(Arrays.equals(array1, array2));

        System.out.println(Arrays.binarySearch(array1, 2));


        StringBuffer bf = new StringBuffer();


        bf.append("hello");

        System.out.println(bf.toString());

        bf.reverse();

        System.out.println(bf.toString());

        bf.replace(0, 1, "xxx");

        System.out.println(bf.toString());


        bf.delete(3, 4);
        System.out.println(bf.toString());

        Runtime runtime = Runtime.getRuntime();


        System.out.println(runtime.freeMemory());
        System.out.println(runtime.maxMemory());



        runtime.gc();

        System.out.println(runtime.freeMemory());
        System.out.println(runtime.maxMemory());



        String ss = new String("123|456|789");

        String[] ssArr = ss.split("\\|");




        Timer t = new Timer();

        t.schedule(new MyTask("my task", t), 0,3000);


        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader bfrd = new BufferedReader(in);

        try {
            System.out.println("请输入一行内容");
            String input = bfrd.readLine();

            System.out.println(input);
        } catch (Exception e) {
            System.out.println(e);
        }




    }
}

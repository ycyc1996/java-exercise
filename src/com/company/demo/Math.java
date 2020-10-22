package com.company.demo;

public class Math {
    public final double PI = 3.1415926;
    public final double zero = 0;
    public final double e = 2.7;



    public static void printDiamond(int n) throws Exception {



        if (n % 2 == 0) {
            throw new Exception("n must be an odd number");
        }
        System.out.println("打印一个菱形");


        int mid = n / 2;

        int st = mid, ed = mid;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                if (j < st || j > ed) {
                    System.out.print((char)32);
                } else {
                    System.out.print('*');
                }
            }

            System.out.print('\n');
            if (i < mid) {
                st--;
                ed++;
            } else {
                st++;
                ed--;
            }
        }

    }
}

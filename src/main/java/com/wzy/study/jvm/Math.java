package com.wzy.study.jvm;

public class Math {
    private int a1;
    private int b2 = 100;
    private String c3;
    private static Math a11 = new Math();

    public void test() {
        int a = 10;
        int b = 10;
        int c = (a + b) * 10;
        int d = (a + b) * 2;
        System.out.println(c);
        System.out.println(d);
    }

    public void M(int m, String n) {
        a1 = m;
        c3 = n;
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.ext.dirs"));
    }

    private static void ma() {
        ma();
    }

}

package com.jenkins.test;

/**
 * @Author: LongYao
 * @Date: 2021/1/30 16:57
 */
public class TestString {

    public static void main(String[] args) {
        test3();
    }

    public static void test1() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 == s4);
    }

    public static void test2() {
        String s3 = new String("ab");
        String s4 = new String("ab");
        System.out.println(s3 == s4);
    }

    public static void test3() {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
        System.out.println("=====");

        String s5 = new String("1") + new String("1");
        String s6 = "11";
        s5.intern();
        System.out.println(s5 == s6);
    }
}

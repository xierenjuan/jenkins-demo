package com.jenkins.test;

import java.util.Date;

/**
 * @Author: LongYao
 * @Date: 2021/1/19 9:12
 */
public class StackOutOfErrorTest {

    public static String str = "abc";

    public static void main(String[] args) {
        StackOutOfErrorTest test = new StackOutOfErrorTest();
        TestJvm testJvm = new TestJvm();
        testJvm.toString();
        int num = 10;
        long l = 10;
        test.test1();
    }

    public void test1(){
        int num = 20;
        {
            int a = 10;
        }

        int b = num;
        System.out.println(b);
    }
}

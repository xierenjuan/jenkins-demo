package com.jenkins.test;

/**
 * @Author: LongYao
 * @Date: 2021/2/3 14:33
 */
public class RefTest {

    public byte[] b = new byte[5 * 1024 * 1024];

    RefTest test = null;

    public static void main(String[] args) {
        RefTest test1 = new RefTest();
        RefTest test2 = new RefTest();
        test1.test = test2;
        test2.test = test1;

        test1 = null;
        test2 = null;

        System.gc();
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

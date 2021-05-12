package com.jenkins.test;

/**
 * @Author: LongYao
 * @Date: 2021/1/20 15:52
 */
public class HeapTest {
    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end/.....");
    }
}

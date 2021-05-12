package com.jenkins.test;

/**
 *
 * @Author: LongYao
 * @Date: 2021/2/22 14:40
 */
public class TestGC {
    public static void main(String[] args) {
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

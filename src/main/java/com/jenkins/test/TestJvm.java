package com.jenkins.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: LongYao
 * @Date: 2021/1/16 9:42
 */
public class TestJvm {
    public static void main(String[] args) {
        int i = 10;
        int j = 20;
        int result = i + j;
        Integer integer = 1;
        System.out.println(result);
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
    }

    public void test1(){
        TestJvm testJvm = new TestJvm();
        testJvm.test2();
    }

    public void test2(){
        System.out.println("=");
    }
}

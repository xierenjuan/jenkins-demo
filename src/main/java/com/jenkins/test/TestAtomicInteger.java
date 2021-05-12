package com.jenkins.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: LongYao
 * @Date: 2021/3/16 10:32
 */
public class TestAtomicInteger {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(10);

        System.out.println(atomicInteger.compareAndSet(10, 30));
        System.out.println(atomicInteger.compareAndSet(10, 30));

        int andIncrement = atomicInteger.getAndIncrement();
    }
}

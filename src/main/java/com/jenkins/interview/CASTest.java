package com.jenkins.interview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: LongYao
 * @Date: 2021/4/7 10:15
 */
public class CASTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.compareAndSet(10, 20);
        System.out.println(atomicInteger.get());
    }
}

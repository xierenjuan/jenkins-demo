package com.jenkins.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: LongYao
 * @Date: 2021/3/9 10:40
 */
public class TestJvmGC {
    public static void main(String[] args) {

        try {
            AtomicInteger atomicInteger = new AtomicInteger();
            int i = atomicInteger.getAndIncrement();
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

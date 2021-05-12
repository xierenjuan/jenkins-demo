package com.jenkins.interview;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author: LongYao
 * @Date: 2021/4/7 10:47
 */
public class ABATest {

    public static void main(String[] args) {
        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(100, 1);

        System.out.println(reference.compareAndSet(100, 200, 1, 2));
        System.out.println(reference.compareAndSet(200, 300, 2, 1));

    }

    private static void normalReference() {
        AtomicReference<Integer> reference = new AtomicReference<>();
        reference.set(1);

        new Thread(() -> {
            System.out.println(reference.compareAndSet(1, 2) + "当前值" + reference.get());
            System.out.println(reference.compareAndSet(2, 1) + "当前值" + reference.get());
        }, "aaa").start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(reference.compareAndSet(1, 2) + "当前值" + reference.get());
        }, "bbb").start();
    }
}

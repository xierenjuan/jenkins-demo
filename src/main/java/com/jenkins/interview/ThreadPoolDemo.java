package com.jenkins.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: LongYao
 * @Date: 2021/4/14 11:10
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int tempi = i;
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " 给 " + tempi + " 办理业务");
            });
        }

        executorService.shutdown();

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();

    }
}

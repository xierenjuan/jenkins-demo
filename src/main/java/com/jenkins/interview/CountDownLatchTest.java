package com.jenkins.interview;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: LongYao
 * @Date: 2021/4/12 10:47
 * 比如， 10个人在教室上自习，其中一个是班长，保证班长最后一个走，关门
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println("学生： " + temp + "走了");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "班长离开");

    }
}

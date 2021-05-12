package com.jenkins.interview;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: LongYao
 * @Date: 2021/4/28 10:01
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " come in， 即将park");
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + " park 完成");
            } catch (Exception e){

            }
        }, "A");
        a.start();

        new Thread(() -> {
            try {
                Thread.sleep(4 * 1000l);
                System.out.println(Thread.currentThread().getName() + " come in 即将调用 unpark(A)");
                LockSupport.unpark(a);
                System.out.println(Thread.currentThread().getName() + " unpark 完成 ");
            } catch (Exception e){

            }
        }, "B").start();
    }

}

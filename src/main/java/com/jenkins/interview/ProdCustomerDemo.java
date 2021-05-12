package com.jenkins.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: LongYao 生产者消费者模式(Lock方式)
 * @Date: 2021/4/13 14:25
 */
public class ProdCustomerDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {shareData.increment();}, "t1").start();
            new Thread(() -> {shareData.decrement();}, "t2").start();
        }
    }
}

class ShareData{
    int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try {
            while (num != 0){
                condition.await();
            }
            num++;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + "\t" + num);
        } catch (Exception e){

        } finally {
            lock.unlock();
        }
    }
    public void decrement(){
        lock.lock();
        try {
            while (num == 0){
                condition.await();
            }
            num--;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + "\t" + num);
        } catch (Exception e){

        } finally {
            lock.unlock();
        }
    }
}

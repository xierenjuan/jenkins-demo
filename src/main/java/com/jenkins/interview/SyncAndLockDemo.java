package com.jenkins.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: LongYao
 * @Date: 2021/4/13 14:38
 */
public class SyncAndLockDemo {

    public static void main(String[] args) {
        ShareData2 shareData2 = new ShareData2();
        new Thread(() -> {shareData2.print5();}, "t1").start();
        new Thread(() -> {shareData2.print10();}, "t2").start();
        new Thread(() -> {shareData2.print15();}, "t3").start();
    }
}

class ShareData2{
    //
    int num = 1 ;
    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            while (num != 1){
                condition1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()  + "打印" + i);
            }
            num = 2;
            condition2.signal();
        } catch (Exception e){

        } finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while (num != 2){
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()  + "打印" + i);
            }
            num = 3;
            condition3.signal();
        } catch (Exception e){

        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while (num != 3){
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()  + "打印" + i);
            }
            num = 1;
            condition1.signal();
        } catch (Exception e){

        } finally {
            lock.unlock();
        }
    }
}

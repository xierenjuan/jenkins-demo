package com.jenkins.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: LongYao
 * @Date: 2021/3/16 14:04
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.write(String.valueOf(temp), String.valueOf(temp));
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.read(String.valueOf(temp));
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();

//    private ReentrantLock lock = new ReentrantLock();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void write(String key, Object value){
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "正在写入：");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入完成");

        lock.writeLock().unlock();
    }

    public void read(String key){
        lock.readLock().lock();;
        System.out.println(Thread.currentThread().getName() + "正在读取：");
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取完成：" + o);
        lock.readLock().unlock();;
    }

}

package com.jenkins.interview;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: LongYao
 * @Date: 2021/4/27 16:23
 */
public class CompletableDemo {
    public static void main(String[] args) throws Exception{
//        noReturn();
        long start = new Date().getTime();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> t4());

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> t5());

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> t6());

        CompletableFuture.allOf(future1, future2, future3);
//        future.get();
        System.out.println("future1 : 结果 ： " + future1.get());
        System.out.println("future2 : 结果 ： " + future2.get());
        System.out.println("future3 : 结果 ： " + future3.get());
        System.out.println(new Date().getTime() - start);
    }

    private static void noReturn() throws InterruptedException, java.util.concurrent.ExecutionException {
        long start = new Date().getTime();
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            t1();
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            t2();
        });

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            t3();
        });

        CompletableFuture<Void> future = CompletableFuture.allOf(future1, future2, future3);
        future.get();
        System.out.println(new Date().getTime() - start);
    }

    public static void t1(){
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void t2(){
        try {
            Thread.sleep(15000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void t3(){
        try {
            Thread.sleep(8000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Integer t4(){
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 3;
    }

    public static Integer t5(){
        try {
            Thread.sleep(15000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 15;
    }

    public static Integer t6(){
        try {
            Thread.sleep(8000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 8;
    }
}

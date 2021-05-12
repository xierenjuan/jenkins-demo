package com.jenkins.interview;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: LongYao
 * @Date: 2021/4/14 10:43
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new CallAble());
        Thread thread = new Thread(futureTask, "aa");
        thread.start();

        System.out.println(futureTask.get());
    }


}

class CallAble implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(10 * 1000);
        System.out.println(Thread.currentThread().getName()  + "进来了");
        return 1024;
    }
}

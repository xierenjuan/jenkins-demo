package com.jenkins.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: LongYao
 * @Date: 2020/11/9 14:17
 */
public class SendMonitor {
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static SendMonitor sendMonitor = null;
    private SendMonitor() {

    }

    public static boolean addData(String data){
        return getInstance().queue.add(data);
    }

    public static SendMonitor getInstance(){
        if(sendMonitor == null){
            synchronized (SendMonitor.class){
                if(sendMonitor == null){
                    sendMonitor = new SendMonitor();

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SendMonitor.sendMonitor.run();
                        }
                    });
                    // 测试的时候，不设置为守护模式
                    // thread.setDaemon(true);
                    thread.start();
                }
            }
        }
        return sendMonitor;
    }

    private void run() {
        while (true){
            try {
                BlockingQueue<String> queue = sendMonitor.queue;
                String take = queue.take();
                System.out.println("收到数据：" + take);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

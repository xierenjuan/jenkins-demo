package com.jenkins.test;

/**
 *  -Xms 用来设置堆空间（年轻代+老年代）的初始内存大小
 *   -X：是jvm运行参数
 *   ms：memory start
 *  -Xmx：用来设置堆空间（年轻代+老年代）的最大内存大小
 *  -Xms600m -Xmx600m -XX:+PrintGCDetails 打印gc信息
 */
public class HeapSpaceInitial {
    public static void main(String[] args) {
        // 返回Java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        // 返回Java虚拟机试图使用的最大堆内存
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("-Xms:" + initialMemory + "M");
        System.out.println("-Xmx:" + maxMemory + "M");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {

        }
    }
}
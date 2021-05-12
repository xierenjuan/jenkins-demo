package com.jenkins.test;

/**
 *  -Xms10m -Xmx10m -Xmn10m -XX:+PrintGCDetails
 *  查看默认GC  -Xms10m -Xmx10m -XX:+PrintcommandLineFlags
 * @Author: LongYao
 * @Date: 2021/3/12 10:42
 */
public class TestGCInfo {

    static final Integer _1MB = 1024 * 1024;
    public static void main(String[] args) {
        byte [] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 *_1MB];
        allocation2 = new byte[2 *_1MB];
        allocation3 = new byte[2 *_1MB];
        allocation4 = new byte[4 *_1MB];
    }
}

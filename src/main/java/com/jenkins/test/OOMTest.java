package com.jenkins.test;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/D:/13对账/heapdump.hprof
 */
public class OOMTest {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        while(true) {
            list.add(new char[1 * 1024 * 1024]);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
package com.jenkins.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: LongYao
 * @Date: 2021/4/7 11:10
 */
public class SafeCollection {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
        // arraylist 的安全版本
        List<Integer> list = new Vector<>();
        // Collections.synchronizedList : 原理是通过方法加syn
//        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
//        List<Integer> list = new CopyOnWriteArrayList<>();

    }
}

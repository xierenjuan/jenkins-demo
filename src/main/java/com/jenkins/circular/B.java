package com.jenkins.circular;

/**
 * @Author: LongYao
 * @Date: 2021/4/20 10:16
 */
public class B {
    private A a;

    public B() {
        System.out.println("实例化 B  =====================");
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
        System.out.println("B 里设置a 成功");
    }
}

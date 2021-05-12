package com.jenkins.circular;

/**
 * @Author: LongYao
 * @Date: 2021/4/20 10:16
 */
public class A {
    private B b;

    public A() {
        System.out.println("实例化 A  =====================");
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
        System.out.println("A 里设置b 成功");
    }
}

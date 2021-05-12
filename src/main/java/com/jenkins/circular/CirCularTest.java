package com.jenkins.circular;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: LongYao
 * @Date: 2021/4/20 10:19
 */
public class CirCularTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        A a = ac.getBean("a", A.class);
        B b = ac.getBean("b", B.class);
    }
}

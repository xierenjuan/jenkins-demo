package com.jenkins.interview;

/**
 * @Author: LongYao
 * @Date: 2021/4/6 15:06
 */
public class VolatileTest {
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(() -> {
            myData.setAge(20);
            myData.out();
        }, "AAA").start();

        // 加上volatile 后 ，会执行结束；
        while (myData.age == 0){

        }
        System.out.println("==================" + myData.getAge());
    }
}

class MyData{

    volatile int age = 0;

    public void out(){
        System.out.println(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}



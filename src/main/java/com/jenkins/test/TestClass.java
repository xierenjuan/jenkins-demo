package com.jenkins.test;

/**
 * @Author: LongYao
 * @Date: 2021/3/12 17:04
 */
public class TestClass {
    public static void main(String[] args) {
        Father f = new Son();
        System.out.println(f.x);//Son.x=0
                                //Son.x=30
                                //20
    }
}
class Father{
    int x = 10;
    public Father() {
        this.print();
        x = 20;
    }
    public void print(){
        System.out.println("Father.x=" + x);
    }
}

class Son extends Father{
    int x = 30;
    public Son() {
        this.print();
        x = 40;
    }
    @Override
    public void print(){
        System.out.println("Son.x=" + x);
    }
}

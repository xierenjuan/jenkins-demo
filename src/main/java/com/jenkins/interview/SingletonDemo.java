package com.jenkins.interview;

/**
 * @Author: LongYao
 * @Date: 2021/4/7 9:59
 */
public class SingletonDemo {

    private SingletonDemo() {
        System.out.println("执行构造方法=========");
    }

    /**
     * 1 、解决单例模式线程安全问题
     */
//    private static SingletonDemo instance = null;
//    public synchronized static SingletonDemo getInstance(){
//        if(instance == null){
//            instance = new SingletonDemo();
//        }
//        return instance;
//    }

    /**
     *  2、双端检锁机制
     */
//    private static SingletonDemo instance = null;
//    public static SingletonDemo getInstance(){
//        if(instance == null){
//            synchronized (SingletonDemo.class){
//                if(instance == null){
//                    instance = new SingletonDemo();
//                }
//            }
//        }
//        return instance;
//    }

    /**
     *  3、双端检锁 + volatile
     */
    private volatile static SingletonDemo instance = null;
    public static SingletonDemo getInstance(){
        if(instance == null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    /**
     * 多线程情况下，单例模式安全
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() ->{
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}

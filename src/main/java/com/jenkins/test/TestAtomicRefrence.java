package com.jenkins.test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: LongYao
 * @Date: 2021/3/16 10:46
 */
public class TestAtomicRefrence {
    public static void main(String[] args) {
        test();
    }
    public static  void test(){
        Order z3 = new Order(1, "z3");
        AtomicReference<Order> atomicReference = new AtomicReference<>(z3);
        System.out.println(atomicReference.compareAndSet(z3, new Order(2, "l4")));
        System.out.println(atomicReference.compareAndSet(z3, new Order(2, "l4")));

    }
}

class Order {

    Integer id;
    String name;

    public Order(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


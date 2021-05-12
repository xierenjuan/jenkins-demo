package com.jenkins.interview;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: LongYao
 * @Date: 2021/4/7 10:34
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {
        User u1 = new User(1, "a");
        User u2 = new User(2, "b");
        AtomicReference<User> reference = new AtomicReference<>();

        reference.set(u1);

        System.out.println(reference.compareAndSet(u1, u2) + "当前user：" + reference.get());
        System.out.println(reference.compareAndSet(u1, u2) + "当前user：" + reference.get());

    }
}

class User{
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

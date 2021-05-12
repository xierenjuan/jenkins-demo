package com.jenkins.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LongYao
 * @Date: 2021/4/27 15:04
 */
public class LRUDemo01 {
    class Node<K, V>{
        K k;
        V v;
        Node prev;
        Node next;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;

        }

        public Node() {
            this.prev = this.next = null;
        }
    }

    class DouleLinkList<K, V>{
        Node<K, V> head;
        Node<K, V> tail;

        public DouleLinkList() {
            this.head = new Node<>();
            this.tail = new Node<>();
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }
        public void addHead(Node<K, V> node) {
            node.next = this.head.next;
            node.prev = this.head;
            this.head.next.prev = node;
            this.head.next = node;
        }

        public void removeNode(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;

        }

        public Node<K, V> getLast() {
            if(this.tail.prev == this.head)
                return null;
            return this.tail.prev;
        }
    }

    private int cacheSize;
    private Map<Integer, Node<Integer, Integer>> map;
    private DouleLinkList<Integer, Integer> douleLinkList;
    public LRUDemo01(int cacheSize) {
        this.cacheSize = cacheSize;
        map = new HashMap<>();
        douleLinkList = new DouleLinkList<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }

        Node<Integer, Integer> node = map.get(key);

        //更新节点位置，将节点移置链表头
        douleLinkList.removeNode(node);
        douleLinkList.addHead(node);

        return node.v;
    }

    public void put(int key, int value) {

        if(map.containsKey(key)) {

            Node<Integer, Integer> node = map.get(key);
            node.v = value;
            map.put(key, node);


            douleLinkList.removeNode(node);
            douleLinkList.addHead(node);
        }else {

            if(map.size() == cacheSize) {//已达到最大容量了，把旧的移除，让新的进来
                Node<Integer, Integer> lastNode = douleLinkList.getLast();
                map.remove(lastNode.k);//node.key主要用处，反向连接map
                douleLinkList.removeNode(lastNode);
            }

            Node<Integer, Integer> newNode = new Node<>(key, value);
            map.put(key, newNode);
            douleLinkList.addHead(newNode);
        }
    }
}

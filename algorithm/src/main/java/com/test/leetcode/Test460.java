package com.test.leetcode;


import java.util.HashMap;
import java.util.Map;

public class Test460 {

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(3,1);
        lfuCache.put(2,1);
        lfuCache.put(2,2);
        lfuCache.put(4,4);
        int i = lfuCache.get(2);
        System.out.println(i);

    }

    public static class LFUCache {
        Map<Integer,Node> cache;
        DoublyLinkedList firstLinkedList;
        DoublyLinkedList lastLinkedList;
        int size;
        int capacity;

        public LFUCache(int capacity){
            cache = new HashMap<>();
            firstLinkedList = new DoublyLinkedList();
            lastLinkedList = new DoublyLinkedList();
            firstLinkedList.post = lastLinkedList;
            lastLinkedList.pre = firstLinkedList;
            this.capacity = capacity;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if(node == null) {
                return -1;
            }
            freqInc(node);
            return node.value;
        }

        public void put(int key , int value){
            if(capacity == 0){
                return;
            }
            Node node = cache.get(key);
            if(node!=null) {
                node.value = value;
                freqInc(node);
            }else{
                if(size == capacity) {
                    // 如果缓存满了，删除lastLinkedList.pre这个链表（即表示最小频次的链表）
                    // 中的tail.pre这个Node（即最小频次链表中最先访问的Node），
                    cache.remove(lastLinkedList.pre.tail.pre.key);
                    lastLinkedList.removeNode(lastLinkedList.pre.tail.pre);
                    size--;
                    //如果该链表中的元素删空了，则删掉该链表。
                    if(lastLinkedList.pre.head.post == lastLinkedList.pre.tail){
                        removeDoublyLinkedList(lastLinkedList.pre);
                    }
                }
                Node newNode = new Node(key,value);
                cache.put(key,newNode);
                if(lastLinkedList.pre.freq!=1){
                    DoublyLinkedList newList = new DoublyLinkedList(1);
                    addDoublyLinkedList(newList,lastLinkedList.pre);
                    newList.addNode(newNode);
                }else{
                    lastLinkedList.pre.addNode(newNode);
                }
                size ++;

            }
        }


        void freqInc(Node node) {
            DoublyLinkedList doublyLinkedList = node.doublyLinkedList;
            DoublyLinkedList pre = doublyLinkedList.pre;
            doublyLinkedList.removeNode(node);
            //移除后这个链表中如果没有元素了，就删掉这个链表。
            if(doublyLinkedList.head.post == doublyLinkedList.tail){
                removeDoublyLinkedList(doublyLinkedList);
            }
            node.freq++;
            if(pre.freq!=node.freq){
                DoublyLinkedList newList = new DoublyLinkedList(node.freq);
                addDoublyLinkedList(newList,pre);
                newList.addNode(node);
            }else{
                pre.addNode(node);
            }

        }

        void removeDoublyLinkedList(DoublyLinkedList doublyLinkedList) {
            doublyLinkedList.pre.post = doublyLinkedList.post;
            doublyLinkedList.post.pre = doublyLinkedList.pre;
        }

        void addDoublyLinkedList(DoublyLinkedList newList, DoublyLinkedList pre) {
            newList.post = pre.post;
            newList.post.pre = newList;
            newList.pre = pre;
            pre.post = newList;
        }

    }


    public static class Node {
        int key;
        int value;
        int freq = 1;
        Node pre;
        Node post;
        DoublyLinkedList doublyLinkedList;
        public Node(){}
        public Node(int key , int value){
            this.key = key;
            this.value = value;
        }

    }
    public static class DoublyLinkedList{
        int freq;
        DoublyLinkedList pre;
        DoublyLinkedList post;
        Node head;
        Node tail;

        public DoublyLinkedList(){
            head = new Node();
            tail = new Node();
            head.post = tail;
            tail.pre = head;
        }
        public DoublyLinkedList(int freq){
            head = new Node();
            tail = new Node();
            head.post = tail;
            tail.pre = head;
            this.freq = freq;
        }
        void removeNode(Node node) {
            node.pre.post = node.post;
            node.post.pre = node.pre;
        }
        void addNode(Node node) {
            node.post = head.post;
            head.post.pre = node;
            head.post = node;
            node.pre = head;
            node.doublyLinkedList  = this;
        }

    }


}

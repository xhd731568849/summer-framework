package com.test.kuaishou;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test2_2 {


    static int count = 0;

    static Lock lock = new ReentrantLock();
    static Condition c1 = lock.newCondition();
    static Condition c2 = lock.newCondition();
    static Condition c3 = lock.newCondition();

    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    while (count % 3 != 0) {
                        c1.await();
                    }
                    System.out.println("A");
                    count++;
                    c2.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    while (count % 3 != 1) {
                        c2.await();
                    }
                    System.out.println("B");
                    count++;
                    c3.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    while (count % 3 != 2) {
                        c3.await();
                    }
                    System.out.println("C");
                    count++;
                    c1.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

}

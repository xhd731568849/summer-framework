package com.test.company.kuaishou;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3个线程循环打印
 */
public class Test2_1 {

    static int count = 0;

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                lock.lock();
                if (count % 3 == 0) {
                    System.out.println("A");
                    count++;

                }
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                lock.lock();
                if (count % 3 == 1) {
                    System.out.println("B");
                    count++;
                }
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                lock.lock();
                if (count % 3 == 2) {
                    System.out.println("C");
                    count++;
                }
                lock.unlock();
            }
        }).start();
    }

}

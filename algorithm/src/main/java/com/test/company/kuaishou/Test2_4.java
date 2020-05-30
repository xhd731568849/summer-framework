package com.test.company.kuaishou;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test2_4 {

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
                    if (count % 3 == 0) {
                        System.out.println("A");
                        count++;
                        c2.signal();
                    } else {
                        c1.await();
                    }
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
                    if (count % 3 == 1) {
                        System.out.println("B");
                        count++;
                        c3.signal();
                    } else {
                        c2.await();
                    }
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
                    if (count % 3 == 2) {
                        System.out.println("C");
                        count++;
                        c1.signal();
                    } else {
                        c3.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }).start();
    }
}

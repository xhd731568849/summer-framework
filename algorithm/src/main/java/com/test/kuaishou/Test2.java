package com.test.kuaishou;

/**
 * 3个线程循环打印
 */
public class Test2 {

    static final Object o = new Object();

    static int count = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                synchronized (o) {
                    if (count % 3 == 0) {
                        System.out.println("A");
                        count++;
                        o.notifyAll();
                    } else {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                synchronized (o) {
                    if (count % 3 == 1) {
                        System.out.println("B");
                        count++;
                        o.notifyAll();
                    } else {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                synchronized (o) {
                    if (count % 3 == 2) {
                        System.out.println("C");
                        count++;
                        o.notifyAll();
                    } else {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

}

package com.summer.demos.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther: xhd
 * @date: 2019-03-29 20:15
 */
public class Test11 {
    AtomicInteger count = new AtomicInteger();

    void f() {
        for (int i = 0; i < 1000; i++) {
            if (count.get() < 1000) {
                count.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) {
        Test11 t = new Test11();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::f, "thread -" + i));
        }
        threads.forEach((o) -> {
            o.start();
        });

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}

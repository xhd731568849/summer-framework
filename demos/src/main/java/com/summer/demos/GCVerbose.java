package com.summer.demos;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 手工制造一个内存震荡曲线，方便验证 GC 配置
 *
 * @author lishuangtao
 */
public class GCVerbose {

    public static BlockingQueue<String> mpool = new ArrayBlockingQueue<String>(10000);

    /**
     * 为了探测GC的执行情况
     */
    public static void main(String[] argv) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                Object b = null;
                while (true) {
                    String eventstr = "Provide Event: " + System.currentTimeMillis();
                    i++;
                    for (int j = 0; j <= 10; j++) {
                        eventstr += eventstr;
                    }
                    try {
                        mpool.offer(eventstr, 100, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (i % 1000 == 0) {
                        System.out.println("offer eventstr:" + i);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        String eventstr = mpool.poll(100, TimeUnit.MILLISECONDS);
                        i++;
                        if (i % 1000 == 0) {
                            System.out.println("get eventstr:" + i);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

package com.summer.demos.test1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * .
 * .
 *
 * @author xhd
 * @date 2019-07-10
 */
public class Test1 {

    public static void main(String[] args) throws Exception {
        Thread.currentThread().interrupt();
        test1();

    }


    /**
     * 测试shutdown
     */
    public static void test1() throws Exception {
        MyPoolExecutor executor = new MyPoolExecutor(2,
                10,
                8L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4));

        executor.prestartAllCoreThreads();
        System.out.println("准备进入for循环 。。。。。 ");
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread() + " 第 " + (finalI + 1) + "个任务 准备处理..");
                try {
                    Thread.sleep(2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " 第 " + (finalI + 1) + "个任务 完成！！");
            });
        }

        Thread.sleep(10000L);
        System.out.println("开始shutdown..");
        executor.shutdown();
    }

}

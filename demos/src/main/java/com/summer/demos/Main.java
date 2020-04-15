package com.summer.demos;

public class Main {

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("完成");

        }).start();
        System.out.println();
    }
}

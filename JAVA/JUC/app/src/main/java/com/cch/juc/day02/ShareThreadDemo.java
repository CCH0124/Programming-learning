package com.cch.juc.day02;

import java.util.logging.Logger;

public class ShareThreadDemo {
    private static Logger log = Logger.getLogger(ShareThreadDemo.class.getName());
    static int c = 0;
    static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (obj) {
                    c++;
                }
            }
            log.info(String.format("T1 c = %d", c));
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (obj) {
                    c--;
                }
            }
            log.info(String.format("T2 c = %d", c));
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info(String.format("c = %d", c));
    }
} 

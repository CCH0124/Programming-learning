package com.example.cch.day04;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.ShareThreadTest")
public class ShareThreadTest {
    static int c = 0;
    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (obj) {
                    c++;
                }
            }
            log.info("T1 c = {}", c);
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (obj) {
                    c--;
                }
            }
            log.info("T2 c = {}", c);
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info("c = {}", c);
    }
}

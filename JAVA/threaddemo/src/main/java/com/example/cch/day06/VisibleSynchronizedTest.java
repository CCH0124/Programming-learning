package com.example.cch.day06;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.VisibleTest")
public class VisibleSynchronizedTest {
    static boolean run = true;
    final static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (run) {
                synchronized (o) {
                    if (!run) {
                        break;
                    }
                }
            }
        }, "t1").start();

        Thread.sleep(1000);
        log.info("t1 Thread stop...");
        synchronized (o) {
            run = false;
        }
    }
}

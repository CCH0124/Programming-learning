package com.example.cch.day04;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.WaitNotifyTest")
public class WaitNotifyTest {
    final static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (o) {
                log.info("run T1...");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                log.info("Other T1....");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                log.info("run T2...");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                log.info("Other T2....");
            }
        }, "t2").start();

        new Thread(() -> {
            synchronized (o) {
                log.info("run T3...");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                log.info("Other T3....");
            }
        }, "t3").start();

        Thread.sleep(2000);

        log.info("Wake up other thread...");

        synchronized (o) {
            o.notify();
        }
        Thread.sleep(2000);

        synchronized (o) {
            log.info("Wake up All Thread...");
            o.notifyAll();
        }

    }
}

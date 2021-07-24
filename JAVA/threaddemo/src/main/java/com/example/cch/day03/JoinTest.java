package com.example.cch.day03;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.JoinTest")
public class JoinTest {
    static int r = 100;
    static int r2 = 200;
    private static void test() throws InterruptedException {
        log.debug("Starting...");
        Thread t1 = new Thread(() -> {
            log.debug("T1 Starting...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            log.debug("T1 Ending...");
            r = 1;
        }, "t1");
        t1.start();
        t1.join();
        log.debug("r = {}", r);
        log.debug("Ending...");
    }

    private static void test1() throws InterruptedException {
        log.debug("Starting...");
        Thread t1 = new Thread(() -> {
            log.debug("T1 Starting...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            log.debug("T1 Ending...");
            r = 1;
        }, "t1");
        long start = System.currentTimeMillis();
        t1.start();
        t1.join(1500);
        long end = System.currentTimeMillis();
        log.debug("r1 = {}, r2 = {}, cost: {}", r, r2, end-start);
        log.debug("Ending...");
    }
    public static void main(String[] args) throws InterruptedException {
        test1();
    }
}

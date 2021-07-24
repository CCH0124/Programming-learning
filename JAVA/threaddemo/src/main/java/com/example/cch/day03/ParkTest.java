package com.example.cch.day03;

import java.util.concurrent.locks.LockSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.ParkTest")
public class ParkTest {
    public static void main(String[] args) throws InterruptedException {
        test();
        log.info("End...");
    }

    private static void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("park...");
            LockSupport.park();
            log.info("unpark...");
            log.info("Interrupter State: {}", Thread.currentThread().isInterrupted());
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}

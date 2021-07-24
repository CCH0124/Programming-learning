package com.example.cch.day03;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.DaemonThreadTest")
public class DaemonThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            log.info("Thread t1 End...");
        }, "t1");    

        t1.setDaemon(true);
        t1.start();

        Thread.sleep(500);

        log.info("Main Thread End...");
    }
}

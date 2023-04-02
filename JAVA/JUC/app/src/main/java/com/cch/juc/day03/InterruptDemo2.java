package com.cch.juc.day03;

import java.util.logging.Logger;

public class InterruptDemo2 {
    private static Logger log = Logger.getLogger(InterruptDemo2.class.getName());
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if(Thread.currentThread().isInterrupted()) {
                    log.info(String.format("%s, interrupt flag: %s", Thread.currentThread().getName(), Thread.currentThread().isInterrupted()));
                    log.info("Terminating");
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                log.info("Hello...");
            }
        }, "t1");
        t1.start();

        Thread.sleep(1000);

        new Thread(() -> {
            t1.interrupt();
        }, "t2").start();
    }
}

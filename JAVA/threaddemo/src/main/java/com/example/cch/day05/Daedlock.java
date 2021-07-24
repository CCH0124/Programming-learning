package com.example.cch.day05;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Daedlock")
public class Daedlock {
    private static Object A = new Object();
    private static Object B = new Object();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                log.info("Lock A");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                synchronized (B) {
                    log.info("Lock B");
                    log.info("Open...");
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (B) {
                log.info("Lock B");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                synchronized (A) {
                    log.info("Lock A");
                    log.info("Open...");
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}

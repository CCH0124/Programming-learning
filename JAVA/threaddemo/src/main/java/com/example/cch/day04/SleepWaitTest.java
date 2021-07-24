package com.example.cch.day04;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.SleepWaitTest")
public class SleepWaitTest {
    private static final Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (o) {
                log.info("Get Lock");
                try {
                    Thread.sleep(200000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        Thread.sleep(1000);

        synchronized(o){
            log.info("Main Get Lock");
        }
    }
}

package com.example.cch.day05;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Livelock")
public class Livelock {
    static volatile int count = 10;
    static final Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            while (count > 0 ) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                count--;
                log.info("count: {}", count);
            }
        }, "t1").start();
        new Thread(() -> {
            while (count < 20 ) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                count++;
                log.info("count: {}", count);
            }
        }, "t2").start();
    }
}

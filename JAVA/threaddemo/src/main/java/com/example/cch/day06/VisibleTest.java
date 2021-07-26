package com.example.cch.day06;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.VisibleTest")
public class VisibleTest {
    static boolean run = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while(run) {

            }
        }, "t1").start();

        Thread.sleep(1000);
        log.info("t1 Thread stop...");
        run = false;
    }
}

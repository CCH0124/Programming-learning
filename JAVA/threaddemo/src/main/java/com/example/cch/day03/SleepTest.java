package com.example.cch.day03;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.SleepTest")
public class SleepTest {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        },"t3");

        log.debug("1 - t3 State: {}", t.getState());
        t.start();

        log.debug("2 - t3 State: {}", t.getState());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        log.debug("3 - t3 State: {}", t.getState());
    }

}

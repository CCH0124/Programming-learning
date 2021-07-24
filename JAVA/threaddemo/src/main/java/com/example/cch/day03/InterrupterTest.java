package com.example.cch.day03;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.InterrupterTest")
public class InterrupterTest {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            log.debug("Sleeping...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                log.debug("WAKE UP...");
                e.printStackTrace();
            }
        },"t3");

        
        t.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.debug("Interrupt...");
        t.interrupt();
    }
}

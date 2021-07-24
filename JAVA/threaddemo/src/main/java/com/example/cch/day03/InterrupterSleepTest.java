package com.example.cch.day03;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.InterrupterSleepTest")
public class InterrupterSleepTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("Sleeping...");
            try {
                Thread.sleep(5000); // sleep、wait、join 被打斷後都會把標記清空設置為 false
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        log.debug("Interrupt...");
        t1.interrupt();

        log.debug("Interrupt Mark: {}", t1.isInterrupted()); // 可用來判斷是否繼續運行或是終止
    }
}

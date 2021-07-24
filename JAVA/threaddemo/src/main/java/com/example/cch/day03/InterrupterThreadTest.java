package com.example.cch.day03;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.InterrupterThreadTest")
public class InterrupterThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(true) {
               Boolean isInterrupter =  Thread.currentThread().isInterrupted();// 獲取當前執行續的打斷標記
               if(isInterrupter){
                   log.debug("Exit...");
                   break;
               }
            }
        }, "t1");

        t1.start();

        Thread.sleep(1000);
        log.debug("interrupt...");

        t1.interrupt();
    }
}

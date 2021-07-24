package com.example.cch.day01;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Async")
public class Async {
    public static void main(String[] args) {
        double begin = System.currentTimeMillis();
        log.info("Start...");
        new Thread(() -> {
            int i = 1000;
            while (i > 1) {
                if (i % 37 == 0) {
                    log.info("Thread Hello");
                }
                i--;
            }
        }).start();

        double end = System.currentTimeMillis();

        log.info("Read Success! Cost: {}", end - begin);
        log.debug("do other things ....");

    }
}

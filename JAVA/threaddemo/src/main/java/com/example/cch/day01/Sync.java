package com.example.cch.day01;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Sync")
public class Sync {
    public static void main(String[] args) throws IOException {
        double begin = System.currentTimeMillis();
        log.info("Start....");
        int i = 1000;
        while (i > 1) {
            if (i % 37 == 0) {
                log.info("Thread Hello");
            }
            i--;
        }
        double end = System.currentTimeMillis();
        log.info("Read Success! Cost: {}", end - begin);

        log.debug("do other things ....");
    }
}

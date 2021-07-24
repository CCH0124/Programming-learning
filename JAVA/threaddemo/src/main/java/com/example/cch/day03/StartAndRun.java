package com.example.cch.day03;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.StartAndRun")
public class StartAndRun {
    public static void main(String[] args) {
        Thread t = new Thread(() -> log.debug("Running..."),"t1");

        t.start();

        log.debug("Main....");
    }
}

package com.example.cch.day02;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.LambdaTest")
public class LambdaTest {
    public static void main(String[] args) {
        // Runnable r = () -> log.debug("Lambda...");
        // Thread t = new Thread(r, "t3");
        // t.start();

        // Method 2

        Thread t = new Thread(() -> {log.debug("Lambda");}, "t4");

        t.start();
    }
}

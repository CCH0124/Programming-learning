package com.example.cch.day03;

import com.example.cch.utils.FileReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.ThreadStateTest")
public class ThreadStateTest {
    public static void main(String[] args) {
        new Thread(() -> {
            FileReader.read("src\\main\\java\\com\\example\\cch\\day01\\ANN.mp4");
        }, "t1").start();

        log.info("Main...");
    }
}

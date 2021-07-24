package com.example.cch.day02;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.ThreadTest")
public class ThreadTest {
    public static void main(String[] args) {
        Thread t = new Thread("t1"){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                // Create Task
                log.debug("Hello");
            }
        };

        t.start();

        log.debug("Main Running");
    }
}

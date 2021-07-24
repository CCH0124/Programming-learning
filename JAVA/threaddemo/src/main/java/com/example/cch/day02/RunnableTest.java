package com.example.cch.day02;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.RunnableTest")
public class RunnableTest {
    public static void main(String[] args) {
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                log.debug("Runnable...");
                
            }
        };

        Thread t = new Thread(runnable, "t2");

        t.start();

        log.debug("Main ...");
    }
}

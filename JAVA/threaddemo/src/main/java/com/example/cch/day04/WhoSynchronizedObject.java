package com.example.cch.day04;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.WhoSynchronizedObject")
public class WhoSynchronizedObject {
    public static void main(String[] args) {
        Number n = new Number();
        Number n2 = new Number();
        Thread t1 = new Thread(() -> {
            log.info("begin t1");
            try {
                n.a();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.info("begin t2");
            n.b();
        }, "t2");

        // Thread t3 = new Thread(() -> {
        //     log.info("begin t3");
        //     n.c();
        // }, "t3");

        t1.start();
        t2.start();
        // t3.start();

    }
}

@Slf4j(topic = "c.Number")
class Number {
    public static synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        log.info("a() method");
    }
    public synchronized void b() {
        log.info("b() method");
    }

    public void c() {
        log.info("c() method");
    }
}

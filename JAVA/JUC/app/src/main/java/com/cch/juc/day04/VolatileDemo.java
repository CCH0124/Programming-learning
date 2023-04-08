package com.cch.juc.day04;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VolatileDemo {
    private static Logger log = Logger.getLogger(VolatileDemo.class.getName());
    static boolean flag = true;
    static volatile boolean flagVolatile = true;

    public static void main(String[] args) throws InterruptedException {
        // noSee(); // 會因為沒收到及時通知，對其它線程也是不可見
        see();
    }

    public static void noSee() throws InterruptedException {
        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            while(flag) {
            }
            log.info(String.format("Thread Name: %s, Flag : %s. Exist.", Thread.currentThread().getName(), flag));
        }, "t1").start();

        Thread.sleep(2000);

        flag = false;

        log.info(String.format("Thread Name: %s, Flag : %s", Thread.currentThread().getName(), flag));
    }

    public static void see() throws InterruptedException {
        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            while(flagVolatile) {
            }
            log.info(String.format("Thread Name: %s, Flag : %s. Exist.", Thread.currentThread().getName(), flagVolatile));
        }, "t1").start();

        Thread.sleep(2000);

        flagVolatile = false;

        log.info(String.format("Thread Name: %s, Flag : %s", Thread.currentThread().getName(), flagVolatile));
    }
}

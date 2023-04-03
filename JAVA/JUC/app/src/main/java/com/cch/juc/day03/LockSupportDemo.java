package com.cch.juc.day03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class LockSupportDemo {
    private static Logger log = Logger.getLogger(LockSupportDemo.class.getName());

    public static void main(String[] args) throws InterruptedException {
        // objectLock();
        // conditionLock();
        parkLock();
    }

    private static void objectLock() throws InterruptedException {
        Object ol = new Object();

        new Thread(() -> {
            synchronized (ol) {
                log.info(String.format("%s come in...", Thread.currentThread().getName()));
                try {
                    ol.wait(); // 交出控制權
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info(String.format("%s wake...", Thread.currentThread().getName()));
            }
        }, "t1").start();

        Thread.sleep(1000);

        new Thread(() -> {
            synchronized (ol) {
                ol.notify();
                log.info(String.format("%s notify...", Thread.currentThread().getName()));
            }
        }, "t2").start();
    }

    private static void conditionLock() throws InterruptedException {
        Lock reentrantLock = new ReentrantLock();
        Condition newCondition = reentrantLock.newCondition();
        new Thread(() -> {
            reentrantLock.lock();
            log.info(String.format("%s come in...", Thread.currentThread().getName()));
            try {
                newCondition.await();
                log.info(String.format("%s wake...", Thread.currentThread().getName()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }, "t1").start();

        Thread.sleep(1000);

        new Thread(() -> {
            reentrantLock.lock();
            try {
                newCondition.signal();
                log.info(String.format("%s notify...", Thread.currentThread().getName()));
            } finally {
                reentrantLock.unlock();
            }
        }, "t2").start();
    }

    private static void parkLock() throws InterruptedException {
       Thread t1 = new Thread(() -> {
                   log.info(String.format("%s come in...", Thread.currentThread().getName()));
                   LockSupport.park();
                   log.info(String.format("%s wake...", Thread.currentThread().getName()));
               }, "t1");
        t1.start();
        Thread.sleep(1000);

        new Thread(() -> {
            LockSupport.unpark(t1);
            log.info(String.format("%s notify...", Thread.currentThread().getName()));
        }, "t2").start();
    }

}

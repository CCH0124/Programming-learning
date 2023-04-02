package com.cch.juc.day02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class ReEntryLockDemo {
    private static Logger log = Logger.getLogger(ReEntryLockDemo.class.getName());
    public static void main(String[] args) {
        // reEntryImplicit();
        // reEntryImplicitMethodBlock();
        reEntryShowMethod();
    }

    private static void reEntryImplicit() {
        final Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                log.info(String.format("%s, 外層", Thread.currentThread().getName()));
                synchronized (o) {
                    log.info(String.format("%s, 中層", Thread.currentThread().getName()));
                    synchronized (o) {
                        log.info(String.format("%s, 內層", Thread.currentThread().getName()));
                    }
                }
            }
        }, "implicit").start();
    }

    public synchronized void m1() {
        log.info(String.format("%s, m1 come in...", Thread.currentThread().getName()));
        m2();
        log.info("end...");
    }

    public synchronized void m2() {
        log.info(String.format("%s, m2 come in...", Thread.currentThread().getName()));
        m3();
    }

    public synchronized void m3() {
        log.info(String.format("%s, m3 come in...", Thread.currentThread().getName()));
    }

    private static void reEntryImplicitMethodBlock() {
        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();
        new Thread(() -> {
            reEntryLockDemo.m1();
        }, "reEntryImplicitMethodBlock").start();
    }

    private static void reEntryShowMethod() {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                log.info(String.format("%s, 外層", Thread.currentThread().getName()));
                lock.lock();
                try {
                    log.info(String.format("%s, 內層", Thread.currentThread().getName()));
                } finally{
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();


        new Thread(() -> {
            lock.lock();
            try {
                log.info(String.format("%s, T2 外層", Thread.currentThread().getName()));
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}


package com.example.cch.day05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TryLockTest")
public class TryLockTest {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        lock.lock();
        log.info("Get Lock");
        new Thread(() -> {
            log.info("Try get lock");
            try {
                if (! lock.tryLock(2, TimeUnit.SECONDS)) {
                    log.info("Can not get lock");
                    return;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.info("Can not get lock");
                return;
            }
            try {
                log.info("Get Lock");
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        Thread.sleep(1000);
        log.info("Main release lock");
        lock.unlock();
        
    }
}

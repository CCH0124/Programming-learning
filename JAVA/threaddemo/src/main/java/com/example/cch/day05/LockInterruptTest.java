package com.example.cch.day05;

import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.LockInterruptTest")
public class LockInterruptTest {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        lock.lock();
        Thread t1 = new Thread(() -> {
            try {
                // 沒有競爭那麼就會獲得鎖；反則會進入阻塞對列，可以被其他執行續用 interrupt 打斷 
                log.info("Try Get Lock");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.info("Don't get lock");
                return;
            }
            log.info("Get lock ...");
            lock.unlock();
        }, "t1");

        t1.start();
        
        Thread.sleep(1000);
        log.info("Interrupt t1");

        t1.interrupt();
        
    }
}

package com.example.cch.day05;

import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.ReentrantLockTest")
public class ReentrantLockTest {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock(); // 鎖對象
        try {
            log.info("Main ....");
            m1();
        } finally {
            lock.unlock();    
        }
    }

    public static void m1() {
        lock.lock(); // 進行第二次加鎖，當 main 方法呼叫 m1() 時
        try {
            log.info("m1 ....");
            m2();
        } finally {
            lock.unlock();    
        }
    }

    public static void m2() {
        lock.lock(); // 
        try {
            log.info("m2 ....");
        } finally {
            lock.unlock();    
        }
    }
}

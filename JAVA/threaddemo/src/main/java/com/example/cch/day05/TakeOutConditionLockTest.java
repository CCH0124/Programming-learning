package com.example.cch.day05;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TakeOutConditionLockTest")
public class TakeOutConditionLockTest {
    static ReentrantLock room = new ReentrantLock();

    // 等待食物的休息室
    static Condition waitFoodSet = room.newCondition();
    // 等待外賣休息室
    static Condition waitTakeOutSet = room.newCondition();

    static boolean hasFood = false;
    static boolean hasTakeout = false;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            room.lock();
            try {
                log.info("Has food ? {}", hasFood);
                while (!hasFood) {
                    log.info("No no no wait...");
                    try {
                        waitFoodSet.await();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                log.info("I can eat...");
            } finally {
                room.unlock();
            }
        }, "Kevin").start();

        new Thread(() -> {
            room.lock();
            try {
                log.info("Takeout exist ? {}", hasTakeout);
                while (!hasTakeout) {
                    log.info("No no no ... Sleep....");
                    try {
                        waitTakeOutSet.await();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                log.info("Takeout arrival...");
            } finally {
                room.unlock();
            }
        }, "Foodpanda").start();

        Thread.sleep(1000);

        new Thread(() -> {
            room.lock();
            try {
                hasTakeout = true;
                waitTakeOutSet.signal(); // 類似 notify
            } finally {
                room.unlock();
            }
        }, "Takeout").start();

        Thread.sleep(1000);

        new Thread(() -> {
            room.lock();
            try {
                hasFood = true;
                waitFoodSet.signal(); // 類似 notify
            } finally {
                room.unlock();
            }
        }, "Food").start();
    }
}

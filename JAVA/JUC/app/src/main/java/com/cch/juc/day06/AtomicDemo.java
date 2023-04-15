package com.cch.juc.day06;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class AtomicDemo {
    private static final Logger log = Logger.getLogger(AtomicDemo.class.getName());

    public static void main(String[] args) throws InterruptedException {
        atominIntegerTest();
    }

    static void atominIntegerTest() throws InterruptedException {
        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        myNumber.addPlus();
                    }
                } finally {
                    countDownLatch.countDown();
                }

            }, String.format("T%d", i)).start();
        }

        // try {
        //     // 等待上面 50 個執行續完成
        //     // 否則 main 線程會有非預期結果
        //     Thread.sleep(2000);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        countDownLatch.await();

        log.info(String.format("Thread Name: %s, Result: %d .", Thread.currentThread().getName(),
                myNumber.atomicInteger.get()));
    }
}

class MyNumber {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlus() {
        atomicInteger.getAndIncrement();
    }
}

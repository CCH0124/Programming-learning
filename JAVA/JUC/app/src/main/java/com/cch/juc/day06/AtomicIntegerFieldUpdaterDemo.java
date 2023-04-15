package com.cch.juc.day06;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Logger;

public class AtomicIntegerFieldUpdaterDemo {
    private static final Logger log = Logger.getLogger(AtomicIntegerFieldUpdaterDemo.class.getName());

    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        // 轉帳 10 次
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 每次轉 1000 元
                    for (int j = 0; j < 1000; j++) {
                        bankAccount.transferMoney(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }

            }, String.format("T%d", i)).start();
        }

        countDownLatch.await();

        log.info(String.format("Thread Name: %s, Result: %d .", Thread.currentThread().getName(),
                bankAccount.money));
    }
}

class BankAccount {
    String name = "TCB";

    public volatile int money = 0;

    AtomicIntegerFieldUpdater fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

    // 保證原子性
    public void transferMoney(BankAccount bankAccount) {
        fieldUpdater.getAndIncrement(bankAccount);
    }

}

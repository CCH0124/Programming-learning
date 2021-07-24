package com.example.cch.day05;

import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

public class PhilosophersDiningTryLockSolutionDeadLock {
    public static void main(String[] args) {
        ChopstickTryLock c1 = new ChopstickTryLock("1");
        ChopstickTryLock c2 = new ChopstickTryLock("2");
        ChopstickTryLock c3 = new ChopstickTryLock("3");
        ChopstickTryLock c4 = new ChopstickTryLock("4");
        ChopstickTryLock c5 = new ChopstickTryLock("5");

        new PhilosophersDiningTryLock("蘇格拉底", c1, c2).start();
        new PhilosophersDiningTryLock("柏拉圖", c2, c3).start();
        new PhilosophersDiningTryLock("亞里斯多德", c3, c4).start();
        new PhilosophersDiningTryLock("赫拉克利特", c4, c5).start();
        new PhilosophersDiningTryLock("阿基米德", c5, c1).start();
    }
}

@Slf4j(topic = "c.PhilosophersDining")
class PhilosophersDiningTryLock extends Thread {
    ChopstickTryLock left;
    ChopstickTryLock right;

    public PhilosophersDiningTryLock(String name, ChopstickTryLock left, ChopstickTryLock right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            if (left.tryLock()) {
                try {
                    if (right.tryLock()) {
                        try {
                            eat();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
        }
    }

    private void eat() throws InterruptedException {
        log.info("Eating...");
        Thread.sleep(1000);
    }
}


/**
 * 讓 ChopstickTryLock 有鎖的能力
 */
class ChopstickTryLock extends ReentrantLock {
    private String name;

    public ChopstickTryLock(String name) {
        this.name = name;
    }
}
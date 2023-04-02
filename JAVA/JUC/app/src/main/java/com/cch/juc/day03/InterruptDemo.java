package com.cch.juc.day03;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class InterruptDemo {
    private static Logger log = Logger.getLogger(InterruptDemo.class.getName());

    static volatile boolean isStop = false;

    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    public static void main(String[] args) throws InterruptedException {
        // volatileDemo();
        // atomicDemo();
        // threadApiDemo();
        interrupteisPauseDemo();
    }


    private static void volatileDemo() throws InterruptedException {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    log.info(String.format("%s, isStop: %s", Thread.currentThread().getName(), isStop));
                    break;
                }
                log.info("t1, is volatile");
            }
        }, "t1").start();
        Thread.sleep(200);
        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }

    private static void atomicDemo() throws InterruptedException {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    log.info(String.format("%s, isStop: %s", Thread.currentThread().getName(), atomicBoolean.get()));
                    break;
                }
                log.info("t1, is atomicBoolean");
            }
        }, "t1").start();
        Thread.sleep(200);
        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }

    private static void threadApiDemo() throws InterruptedException {
        Thread t1 = new Thread(() -> {
                    while (true) {
                        if (Thread.currentThread().isInterrupted()) {
                            log.info(String.format("%s, isStop: %s", Thread.currentThread().getName(), Thread.currentThread().isInterrupted()));
                            break;
                        }
                        log.info("t1, is interrupt api.");
                    }
            }, "t1");
        t1.start();
        Thread.sleep(150);
        new Thread(() -> {
            t1.interrupt(); // 設置為 true
            log.info(String.format("%s, Mark t1: %s", Thread.currentThread().getName(), t1.isInterrupted()));
        }, "t2").start();
    }

    private static void interrupteisPauseDemo() throws InterruptedException {
        Thread t1 = new Thread(() -> {
                    for (int i = 0; i < 10; i++) {
                        log.info(String.format("i: %d", i));
                    }
                    log.info(String.format("called interrupt() flag: %s", Thread.currentThread().isInterrupted()));
                }, "t1");
        t1.start();

        log.info(String.format("t1 default interrupt flag: %s", t1.isInterrupted()));

        Thread.sleep(2);

        t1.interrupt();
        log.info(String.format("%s called interrupt() flag: %s", Thread.currentThread().getName() ,t1.isInterrupted()));

        Thread.sleep(2000);
        log.info("Wating 2000 millis");
        log.info(String.format("%s called interrupt() 之後的 flag: %s", Thread.currentThread().getName() ,t1.isInterrupted()));
    }


}

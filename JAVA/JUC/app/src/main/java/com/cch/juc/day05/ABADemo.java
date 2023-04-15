package com.cch.juc.day05;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.logging.Logger;

public class ABADemo {

    private static final Logger log = Logger.getLogger(CompareAndSwapDemo.class.getName());
    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100, 1);
    public static void main(String[] args) {
        // abaTest();
        abaAvoidTest();
    }

    static void abaTest() {
        // 異動的中間過程不可知
        new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(String.format("%s,  atomicInteger: %d", atomicInteger.compareAndSet(100, 2023), atomicInteger.get()));

            
        }, "t2").start();
    }

    static void abaAvoidTest() {
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            log.info(String.format("Thread Name: %s, Version: %d", Thread.currentThread().getName(), stamp));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            log.info(String.format("Thread Name: %s, Upgeade Version: %d", Thread.currentThread().getName(), atomicStampedReference.getStamp()));

            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            log.info(String.format("Thread Name: %s, Upgeade Version: %d", Thread.currentThread().getName(), atomicStampedReference.getStamp()));
        }, "t1").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            log.info(String.format("Thread Name: %s, Version: %d", Thread.currentThread().getName(), stamp));
            try {
                // 等待 T1 發生 ABA
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean compareAndSet = atomicStampedReference.compareAndSet(100, 2023, stamp, stamp + 1);;
            log.info(String.format("%s,  current Object: %d, Version: %d", compareAndSet, atomicStampedReference.getReference(), atomicStampedReference.getStamp()));
            
        }, "t2").start();
    }
}

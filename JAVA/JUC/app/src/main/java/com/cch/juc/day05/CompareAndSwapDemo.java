package com.cch.juc.day05;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class CompareAndSwapDemo {
    private static final Logger log = Logger.getLogger(CompareAndSwapDemo.class.getName());
    public static void main(String[] args) {
        test();
    }

    static void test() {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean compareAndSet = atomicInteger.compareAndSet(5, 2023);
        log.info(String.format("isMatch: %s, value: %d", compareAndSet, atomicInteger.get()));
        boolean compareAndSet2 = atomicInteger.compareAndSet(5, 2023);
        log.info(String.format("isMatch: %s, value: %d", compareAndSet2, atomicInteger.get()));
    }
}

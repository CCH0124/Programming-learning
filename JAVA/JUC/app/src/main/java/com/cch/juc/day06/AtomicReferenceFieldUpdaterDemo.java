package com.cch.juc.day06;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Logger;

public class AtomicReferenceFieldUpdaterDemo {
    private static final Logger log = Logger.getLogger(AtomicReferenceFieldUpdaterDemo.class.getName());
    public static void main(String[] args) {
        Test test = new Test();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    test.init(test);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }, String.format("T%d", i)).start();
        }

    }
    
}

class Test {
    private static final Logger log = Logger.getLogger(Test.class.getName());
    public volatile Boolean init = Boolean.FALSE;

    AtomicReferenceFieldUpdater<Test, Boolean> referenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Test.class, Boolean.class, "init");

    public void init(Test test) throws InterruptedException {
        if (referenceFieldUpdater.compareAndSet(test, Boolean.FALSE, Boolean.TRUE)) {
            log.info(String.format("Thread Name: %s, Start init.", Thread.currentThread().getName()));
            Thread.sleep(3000);
            log.info(String.format("Thread Name: %s, Init end.", Thread.currentThread().getName()));
        } else {
            log.info(String.format("Thread Name: %s, Init has been finished.", Thread.currentThread().getName()));
        }
    }
}

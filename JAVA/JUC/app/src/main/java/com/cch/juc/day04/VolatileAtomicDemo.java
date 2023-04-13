package com.cch.juc.day04;

import java.util.logging.Logger;

public class VolatileAtomicDemo {
    private static final Logger log = Logger.getLogger(VolatileAtomicDemo.class.getName());
    public static void main(String[] args) throws InterruptedException {
        // synchronizedTest();
        volatileTest();
    }
    private static void synchronizedTest() throws InterruptedException {
        Number number = new Number();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    number.addPlus();
                }
            }, String.format("t%d", i)).start();
        }

        Thread.sleep(100);

        log.info(String.format("Thread Name: %s, Non Volatile result: %d", Thread.currentThread().getName(), number.number));
    }

    private static void volatileTest() throws InterruptedException {
        Volatilenumber volatilenumber = new Volatilenumber();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    volatilenumber.addPlus();
                }
            }, String.format("t%d", i)).start();
        }

        Thread.sleep(100);

        log.info(String.format("Thread Name: %s, Volatile result: %d", Thread.currentThread().getName(), volatilenumber.numberVolatile));
    }
}


class Number {
    int number;
    
    public synchronized void addPlus() {
        number++;
    }
    
}


class Volatilenumber {
    volatile int numberVolatile;

    public void addPlus() {
        numberVolatile++;
    }
}
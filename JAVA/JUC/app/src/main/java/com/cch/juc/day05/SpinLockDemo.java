package com.cch.juc.day05;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class SpinLockDemo {
    private static final Logger log = Logger.getLogger(CompareAndSwapDemo.class.getName());
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        // atomicReference.compareAndSet(null, thread) 沒被占用就進去
        log.info(String.format("Thread Name: %s, come in.", Thread.currentThread().getName()));
        while (!atomicReference.compareAndSet(null, thread)) {
            
        }
        
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        log.info(String.format("Thread Name: %s, take over.", Thread.currentThread().getName()));
    }
    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()-> {
            spinLockDemo.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "A").start();

        Thread.sleep(500);

        new Thread(()-> {
            spinLockDemo.lock();
            spinLockDemo.unlock();
        }, "B").start();


    }
}

package com.lab.cch.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解決執行續安全問題 Lock 方式
 *
 */
class Windows2 implements  Runnable {
    private int ticket = 0;
    private ReentrantLock lock = new ReentrantLock(true);
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (ticket < 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ++ticket;
                    System.out.println(Thread.currentThread().getName() + " 賣票, 票號" + ticket);
                } else {
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
public class Demo04 {
    public static void main(String[] args) {
        Windows2 w = new Windows2();
        Thread w1 = new Thread((w));
        Thread w2 = new Thread((w));
        Thread w3 = new Thread((w));
        w1.setName("Windows 1");
        w2.setName("Windows 2");
        w3.setName("Windows 3");
        w1.start();
        w2.start();
        w3.start();
    }
}

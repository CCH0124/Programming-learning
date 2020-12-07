package com.lab.cch.thread;
/**
 * Demo02 改進
 */
class WindowPla implements Runnable {
    private int ticket = 0;
    Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
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
            }
        }
    }
}
public class Demo03 {
    public static void main(String[] args) {
        WindowPla w = new WindowPla();
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
package com.lab.cch.thread;

class Number implements Runnable {
    private int num = 1;
    @Override
    public void run() {
        while (true){
            synchronized (this) {

                notify();

                if (num <= 100) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + num);
                    num++;

                    try {
                        // 當調用 wait 執行續會進入阻塞狀態
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}
public class Demo05 {
    public static void main(String[] args) {
        Number num = new Number();
        Thread t1 = new Thread(num);
        Thread t2 = new Thread(num);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}

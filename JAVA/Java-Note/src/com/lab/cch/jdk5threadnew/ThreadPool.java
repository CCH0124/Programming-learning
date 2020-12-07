package com.lab.cch.jdk5threadnew;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class NumberThread2 implements Runnable {
    @Override
    public void run() {
        for (int i=1; i<=100; i++){
            if(i%2==0){
                System.out.println(Thread.currentThread().getName() + ": "+ i);
            }
        }
    }
}

class NumberThread3 implements Runnable {
    @Override
    public void run() {
        for (int i=1; i<=100; i++){
            if(i%2!=0){
                System.out.println(Thread.currentThread().getName() + ": "+ i);
            }
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        // 提供數量
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service = (ThreadPoolExecutor)executorService;
        service.setCorePoolSize(15);
        // 設置執行續屬性
//        System.out.println(executorService.getClass());
        // 執行指定的執行續操作
        executorService.execute(new NumberThread2());// 適用 Runnable
        executorService.execute(new NumberThread3());
//        executorService.submit(); // 適用 callable
        executorService.shutdown(); // 關閉連接池
    }
}

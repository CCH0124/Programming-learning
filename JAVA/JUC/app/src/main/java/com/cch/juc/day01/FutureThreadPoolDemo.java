package com.cch.juc.day01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class FutureThreadPoolDemo {
    private static Logger log = Logger.getLogger(FutureThreadPoolDemo.class.getName());

    public static void main(String[] args) {
        log.info(String.format("%s start...", Thread.currentThread().getName()));
        // mainProcesser();

        // try {
        //     multiProcesser();
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (ExecutionException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        
        try {
            pollProcesser();
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        log.info(String.format("%s end...", Thread.currentThread().getName()));

    }

    private static void mainProcesser() {
        log.info("start...");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("end...");
    }

    private static void multiProcesser() throws InterruptedException, ExecutionException {
        log.info(String.format("%s start...", Thread.currentThread().getName()));
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        FutureTask<String> f1 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                log.info(String.format("%s Task1...", Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task1 Over";
        });
        threadPool.submit(f1);

        FutureTask<String> f2 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                log.info(String.format("%s Task2...", Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task2 Over";
        });
        threadPool.submit(f2);

        FutureTask<String> f3 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                log.info(String.format("%s Task3...", Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task3 Over";
        });
        threadPool.submit(f3);


        log.info(f1.get());
        log.info(f2.get());
        log.info(f3.get());

        log.info(String.format("%s end...", Thread.currentThread().getName()));

        threadPool.shutdown();
    }


    private static void pollProcesser() throws InterruptedException, ExecutionException {
        log.info(String.format("%s start...", Thread.currentThread().getName()));

        FutureTask<String> f1 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                log.info(String.format("%s Task1...", Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task1 Over";
        });

        new Thread(f1, "t1").start();

        log.info(String.format("%s end...", Thread.currentThread().getName()));

        while(true) {
            if(f1.isDone()) {
                log.info(f1.get());
                return;
            } else {
                log.info("doing...");
            }
        }
    }
}

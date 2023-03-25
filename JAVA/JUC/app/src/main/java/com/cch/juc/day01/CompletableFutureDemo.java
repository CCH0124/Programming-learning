package com.cch.juc.day01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CompletableFutureDemo {

    private static Logger log = Logger.getLogger(CompletableFutureDemo.class.getName());
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        asyncWithoutReturn();
        supplyReturn();
    }

    public static void asyncWithoutReturn() {
        CompletableFuture.runAsync(() -> {
            log.info(String.format("asyncWithoutReturn: %s", Thread.currentThread().getName()));
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void supplyReturn() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                    log.info(String.format("asyncWithoutReturn: %s", Thread.currentThread().getName()));
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Hello";
                });
        log.info(completableFuture.get());
    }
}

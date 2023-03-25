package com.cch.juc.day01;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CompletableFutureDemo {

    private static Logger log = Logger.getLogger(CompletableFutureDemo.class.getName());
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // asyncWithoutReturn();
        // supplyReturn();
        pipelineDemoe();
        log.info(String.format("Main: %s", Thread.currentThread().getName()));
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

    public static void pipelineDemoe() {
        ExecutorService threadPools = Executors.newFixedThreadPool(3);
        try  {
            CompletableFuture.supplyAsync(() -> {
                log.info(String.format("pipelineDemoe: %s", Thread.currentThread().getName()));
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return result;
            }, threadPools)
            .whenComplete((v, e) -> {
                if(Objects.isNull(e)){
                    log.info(String.format("Update: %d", v));
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                log.warning(e.getMessage());
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPools.shutdown();
        }
    }
}

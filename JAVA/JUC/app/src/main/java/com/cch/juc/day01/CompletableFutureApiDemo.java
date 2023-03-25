package com.cch.juc.day01;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CompletableFutureApiDemo {
    private static Logger log = Logger.getLogger(CompletableFutureApiDemo.class.getName());
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // getValue();
        // complete();
        // thenApply();
        // thenAccept();
        // executionOrder();
        // applyToEither();
        thenCombine();
        log.info(String.format("main: %s", Thread.currentThread().getName()));
    }

    public static void getValue() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                    log.info(String.format("getValue: %s", Thread.currentThread().getName()));
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return "Hello";
                });
        log.info(completableFuture.getNow("Replace"));
    }

    public static void complete() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                    log.info(String.format("complete: %s", Thread.currentThread().getName()));
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return "Hello";
                });
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                log.info(String.format("complete other: %s", Thread.currentThread().getName()));
                log.info(String.format("%s \t %s", completableFuture.complete("complete"), completableFuture.join()));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "complete other").start();
    }

    public static void thenApply() throws InterruptedException, ExecutionException {
        ExecutorService threadPools = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                    log.info(String.format("thenApply: %s", Thread.currentThread().getName()));
                    log.info(String.format("Step 1: %s", Thread.currentThread().getName()));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return 1;
                }, threadPools).thenApply(f -> {
                    log.info(String.format("Step 2: %s", Thread.currentThread().getName()));
                    return f + 2;
                }).thenApply(f -> {
                    log.info(String.format("Step 3: %s", Thread.currentThread().getName()));
                    return f + 3;
                }).whenComplete((v, e) -> {
                    if(Objects.isNull(e)) {
                        log.info(String.format("total: %d", v));
                    }
                }).exceptionally(e -> {
                    e.printStackTrace();
                    log.warning(e.getMessage());
                    return 0;
                });
                threadPools.shutdown();
    }

    public static void thenAccept() throws InterruptedException, ExecutionException {
        ExecutorService threadPools = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
                    log.info(String.format("thenApply: %s", Thread.currentThread().getName()));
                    log.info(String.format("Step 1: %s", Thread.currentThread().getName()));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return 1;
                }, threadPools).thenApply(f -> {
                    log.info(String.format("Step 2: %s", Thread.currentThread().getName()));
                    return f + 2;
                }).thenApply(f -> {
                    log.info(String.format("Step 3: %s", Thread.currentThread().getName()));
                    return f + 3;
                }).thenAccept(r -> {
                    log.info(String.format("result: %d", r));
                });

                threadPools.shutdown();
    }

    public static void executionOrder() {
        log.info(String.format("thenRun: %d", CompletableFuture.supplyAsync(() -> 1).thenRun(() -> {}).join()));
        log.info(String.format("thenAccept: %d", CompletableFuture.supplyAsync(() -> 1).thenAccept(System.out::println).join()));
        log.info(String.format("thenApply: %d", CompletableFuture.supplyAsync(() -> 1).thenApply(r -> r * 2).join()));
    }

    public static void applyToEither() {
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
                    log.info("Player A come in");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
        
                    return "A";
                });

        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
                    log.info("Player B come in");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
        
                    return "B";
                });
        
                CompletableFuture<String> applyToEither = playA.applyToEither(playB, f -> {return f + " is win.";});

                log.info(String.format("applyToEither: %s, %s", Thread.currentThread().getName(), applyToEither.join()));
    }

    public static void thenCombine() {
        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> {
            log.info(String.format("a: %s", Thread.currentThread().getName()));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return 10;
        });

        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> {
            log.info(String.format("b: %s", Thread.currentThread().getName()));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return 20;
        });

        CompletableFuture<Integer> thenCombine = a.thenCombine(b, (x , y) -> { return x*y;});

        log.info(String.format("result: %d", thenCombine.join()));
    }
}

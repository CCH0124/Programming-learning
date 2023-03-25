## CompletableFuture
### Future Interface
定義了操作異步任務執行一些方法，如獲取異步任務執行結果、取消任務的執行、判斷任務是否被取消、判斷任務執行是否完成等。

像是主線程讓一個子線程執行任務，子線程可能比較耗時，啟動子線程開始執行任務後，主線程就去做其他事情了，忙其他事情或者先執行完，過一段時間才去獲取子任務的執行結果或是變更的任務狀態。

>可以為主線程開一個分支任務，專門為主線程處裡耗時和費力的業務。

### FutureTask 異步任務
Future 提供一個**異步并行計算**功能。

|實現類|multiple threads|Return Values|異步任務|Exception Handling|
|---|---|---|---|---|
|Runnable|V|X||X|
|Callable|V|V||V|
|||||
|||||


- [runnable vs callable](https://www.baeldung.com/java-runnable-callable)

FutureTask 類別實現了 `Runnable`、`Future<V>` 和 `RunnableFuture<v>`。


```java
        FutureTask<Integer> task = new FutureTask<>(
            new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    // TODO Auto-generated method stub
                    log.info("running...");
                    Thread.sleep(1000); // 影響 get() 取值時間
 
                    return 100;
                }
            }
        );
        Thread t = new Thread(task, "t5");
        t.start();
        log.info(String.format("Get: %s", task.get())); // 獲取任務結果
// Mar 19, 2023 4:24:30 PM com.cch.juc.day01.FutureTaskTest$1 call
// INFO: running...
// Mar 19, 2023 4:24:31 PM com.cch.juc.day01.FutureTaskTest main
// INFO: Get: 100
```

Future 結合線程池線程任務配合，能提高程序執行效率。

模擬三個任務，且只有一個 main 線程處理，約耗時 1 秒，如下


```java

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

// .argfile com.cch.juc.day01.FutureThreadPoolDemo 
// Mar 19, 2023 4:37:26 PM com.cch.juc.day01.FutureThreadPoolDemo mainProcesser
// INFO: start...
// Mar 19, 2023 4:37:27 PM com.cch.juc.day01.FutureThreadPoolDemo mainProcesser
// INFO: end...
```


Thread Pool 加上執行續的範例

```java
    private static void multiProcesser() throws InterruptedException, ExecutionException {
        log.info("start...");
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        FutureTask<String> f1 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                log.info("Task1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task1 Over";
        });
        threadPool.submit(f1);

        FutureTask<String> f2 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                log.info("Task2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task2 Over";
        });
        threadPool.submit(f2);

        FutureTask<String> f3 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                log.info("Task3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task3 Over";
        });
        threadPool.submit(f3);


        log.info(f1.get());
        log.info(f2.get());
        log.info(f3.get());

        log.info("end...");

        threadPool.shutdown();
    }
```

**阻塞**

但對於 `FutureTask.get()` 會是阻塞，所以效率會降低。但對於 `get()` 另一種方式可以限制我要多久內拿到資料否則就拋出 `TimeOutException` 異常。

```java
log.info(f2.get(3, TimeUnit.SECONDS)); // 3 秒內要拿到資料
```
**輪詢**

另一種方式是透過輪詢整體更為優雅，使用 `isDone` 方法。但會有耗費而外資源做輪詢。

```java
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
```

## CompletableFuture
對於 `FutureTask.get()` 會是阻塞，所以效率會降低。`isDone` 會耗費 CPU 資源。`CompletableFuture`提供了一種觀察者模式類似機制，可以讓任務執行完成後通知監聽一方。

`CompletableFuture` 實現了 `Future` 和 `CompletionStage`。

`CompletionStage` 表示異步計算過程某一個階段，一個階段完成後可能會觸發另一個階段。可以用以下靜態方法建立一個異步任務

- runAsync
  - 無返回值
- supplyAsync
  - 有返回值

上述無指定 `Executor` 的方法，預設是 `ForkJoinPool.commonPool()`，作為線程池。


`completableFuture.get()` 與 `completableFuture.join()` 兩者區別在於會不會在編譯拋出異常。

[範例](app/src/main/java/com/cch/juc/day01/CompletableFutureDemo.java)

### 獲取結果和觸發計算
**獲取結果**
- get()
  - 需拋出異常
- get(long timeout, TimeUnit unit)
  - 在 timeout 時間內獲取值，否則拋出異常
- join()
- getRow(T valueIfAbsent)
  - 在還沒計算完成時，給一個替代結果
  - 立即獲取結果，不阻塞。算完，回傳計算後結果；否則回傳替代值(valueIfAbsent)
  - [範例](app/src/main/java/com/cch/juc/day01/CompletableFutureApiDemo.java)


```java
public class CompletableFutureApiDemo {
    private static Logger log = Logger.getLogger(CompletableFutureApiDemo.class.getName());
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        getValue();
    }

    public static void getValue() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                    log.info(String.format("getValue: %s", Thread.currentThread().getName()));
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Hello";
                });
        log.info(completableFuture.getNow("Replace"));
    }
}
// Mar 25, 2023 5:58:56 PM com.cch.juc.day01.CompletableFutureApiDemo getValue
// INFO: Replace
// Mar 25, 2023 5:58:56 PM com.cch.juc.day01.CompletableFutureApiDemo lambda$0
// INFO: getValue: ForkJoinPool.commonPool-worker-1
```
**主動觸發計算**
- complete(T value)
  - 是否打斷 get 方法立即返回 value 值

```java
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

// complete other 執行續等待時間為 5 秒時
// Mar 25, 2023 6:16:32 PM com.cch.juc.day01.CompletableFutureApiDemo lambda$1
// INFO: complete: ForkJoinPool.commonPool-worker-1
// Mar 25, 2023 6:16:32 PM com.cch.juc.day01.CompletableFutureApiDemo main
// INFO: main: main
// Mar 25, 2023 6:16:37 PM com.cch.juc.day01.CompletableFutureApiDemo lambda$2
// INFO: complete other: complete other
// Mar 25, 2023 6:16:37 PM com.cch.juc.day01.CompletableFutureApiDemo lambda$2
// INFO: false      Hello

// complete other 執行續等待時間為 1 秒時
// Mar 25, 2023 6:16:48 PM com.cch.juc.day01.CompletableFutureApiDemo main
// INFO: main: main
// Mar 25, 2023 6:16:48 PM com.cch.juc.day01.CompletableFutureApiDemo lambda$1
// INFO: complete: ForkJoinPool.commonPool-worker-1
// Mar 25, 2023 6:16:49 PM com.cch.juc.day01.CompletableFutureApiDemo lambda$2
// INFO: complete other: complete other
// Mar 25, 2023 6:16:49 PM com.cch.juc.day01.CompletableFutureApiDemo lambda$2
// INFO: true       complete
```

### 對依賴結果進行處理
- thenApply
  - 計算結果存在依賴關係，線程串行化
  - 當前步驟中錯誤，不走下一步，就叫停

```java
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
```
- handle
  - 計算結果存在依賴關係，線程串行化
  - 有異常可以繼續往下個步驟移動


```
exceptionally ---------> try/catch

whenComplete   ------
                     \_______
                       ______    try/finally
                      /
handle---------------->
```

### 對依賴結果進行消費
接受任務的處理結果，並消費處理，無返回結果。

- thenAccept()

```java
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
```

任務執行順序，

- thenRun
  - 任務 A 執行完執行 B，且 B 不需要 A 的結果
- thenAccept
  - 任務 A 執行完執行 B，B 需要 A 的結果，但任務 B 無返回值
- thenApply
  - 任務 A 執行完執行 B，B 需要 A 的結果，同時任務 B 有返回值
[範例](app/src/main/java/com/cch/juc/day01/CompletableFutureApiDemo.java)
```java
    public static void executionOrder() {
        log.info(String.format("thenRun: %d", CompletableFuture.supplyAsync(() -> 1).thenRun(() -> {}).join()));
        log.info(String.format("thenAccept: %d", CompletableFuture.supplyAsync(() -> 1).thenAccept(System.out::println).join()));
        log.info(String.format("thenApply: %d", CompletableFuture.supplyAsync(() -> 1).thenApply(r -> r * 2).join()));
    }
```


**有無 Async 區別**
1. 沒有傳入線程池，預設使用 `ForkJoinPool`
2. 如果傳入自定義線程池，如果執行第一個任務的時候，傳入一個自定義線程池
- 調用 thenRun 方法執行第二個任務時，則第二和第一個任務是共用同一個線程池
- 調用 thenRunAsync 方法執行第二個任務時，第一個任務使用的是自己傳入的線程池，第二個任務是 `ForkJoin` 線程池

>基於系統優化原則，會直接使用 main 線程

### 針對計算速度進行選用
誰快誰用
- applyToEither

```java
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
// Mar 25, 2023 9:24:17 PM com.cch.juc.day01.CompletableFutureApiDemo lambda$18
// INFO: Player A come in
// Mar 25, 2023 9:24:17 PM com.cch.juc.day01.CompletableFutureApiDemo lambda$19
// INFO: Player B come in
// Mar 25, 2023 9:24:18 PM com.cch.juc.day01.CompletableFutureApiDemo applyToEither
// INFO: applyToEither: main, B is win.
// Mar 25, 2023 9:24:18 PM com.cch.juc.day01.CompletableFutureApiDemo main
// INFO: main: main
```

### 對計算結果進行合併
兩個 CompletionStage 任務都完成後，最後能把兩個任務的結果一起交給 thenCombine 來處理。這當中先完成任務的會等待其他未完成的任務。

- thenCombine

```java
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
// Mar 25, 2023 9:32:15 PM com.cch.juc.day01.CompletableFutureApiDemo lambda$22
// INFO: b: ForkJoinPool.commonPool-worker-2
// Mar 25, 2023 9:32:15 PM com.cch.juc.day01.CompletableFutureApiDemo lambda$21
// INFO: a: ForkJoinPool.commonPool-worker-1
// Mar 25, 2023 9:32:18 PM com.cch.juc.day01.CompletableFutureApiDemo thenCombine
// INFO: result: 200
// Mar 25, 2023 9:32:18 PM com.cch.juc.day01.CompletableFutureApiDemo main
// INFO: main: main
```

## 鎖

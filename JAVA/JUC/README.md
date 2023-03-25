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
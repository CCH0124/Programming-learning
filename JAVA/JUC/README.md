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
### 樂觀鎖和悲觀鎖

**悲觀鎖**

認為在自己使用數據的時候一定有別的線程來修改數據，因此在獲取數據的時候會先加鎖，確保數據不會被別的線程修改。

適合應用於寫操作多的場景，先加鎖可以保證寫操作數據正確。

>`synchronized` 和 `Lock` 的實現類都是悲觀鎖


**樂觀鎖**
在使用數據時不會有別的線程來修改數據或資源，所以不添加鎖。

判斷規則
1. 版本號機制
2. CAS演算法，在原子類(Atomic)使用該演算法實現

適合應用於讀操作多的場景，不加鎖可讓操作效能提升。


### 鎖案例

1. 標準存取 mail 和 sns 線程，執行順序

```java
    public static void caseOne() {
        Phone phone = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMail();
        }, "mail").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendSMS();
        }, "sms").start();
    }
// INFO: Thread Name: mail
// Send Mail
// Mar 26, 2023 7:35:58 PM com.cch.juc.day02.LockDemo main
// INFO: Thread Name: main
// Mar 26, 2023 7:35:58 PM com.cch.juc.day02.LockDemo lambda$1
// INFO: Thread Name: sms
// Send SMS
```

因為 `TimeUnit.MILLISECONDS.sleep(200);` 保證 mail 先執行

2. sendMail 方法中加入暫停 3 秒，請問打印順序

```java
// Phone 類別的 Mail 增加暫停
    public synchronized void sendMailCaseTwo() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Send Mail");
    }
```
```java

    public static void caseTwo() {
        Phone phone = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseTwo();
        }, "mail").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendSMS();
        }, "sms").start();
    }

// Mar 26, 2023 7:45:11 PM com.cch.juc.day02.LockDemo lambda$2
// INFO: Thread Name: mail
// Mar 26, 2023 7:45:11 PM com.cch.juc.day02.LockDemo main
// INFO: Thread Name: main
// Mar 26, 2023 7:45:11 PM com.cch.juc.day02.LockDemo lambda$3
// INFO: Thread Name: sms
// Send Mail
// Send SMS
```

3. 添加一個 Hello 方法，是 mail 還是 hello 先打印

```java
// Phone 類別增加 hello()
    public void hello() {
        System.out.println("hello");
    }
```

```java
    public static void caseThree() {
        Phone phone = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseTwo();
        }, "mail").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.hello();
        }, "hello").start();
    }
// Mar 26, 2023 7:51:35 PM com.cch.juc.day02.LockDemo lambda$4
// INFO: Thread Name: mail
// Mar 26, 2023 7:51:35 PM com.cch.juc.day02.LockDemo main
// INFO: Thread Name: main
// Mar 26, 2023 7:51:35 PM com.cch.juc.day02.LockDemo lambda$5
// INFO: Thread Name: hello
// hello
// Send Mail
```

4. 兩部手機(Phone 類別)，先 mail 還是 sms，一部手機只有 mail，另外一個只有 sms

```java
    public static void caseFour() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseTwo();
        }, "phone1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone2.sendSMS();
        }, "phone2").start();
    }
// Mar 26, 2023 7:58:13 PM com.cch.juc.day02.LockDemo lambda$6
// INFO: Thread Name: phone1
// Mar 26, 2023 7:58:13 PM com.cch.juc.day02.LockDemo main
// INFO: Thread Name: main
// Mar 26, 2023 7:58:13 PM com.cch.juc.day02.LockDemo lambda$7
// INFO: Thread Name: phone2
// Send SMS
// Send Mail
```

5. 有兩個靜態同步方法，一部手機，先打印 mail 還是 sms

```java
// Phone 類別增加
    public static synchronized void sendMailCaseFive() {
         try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("Send Mail");
    }

    public static synchronized void sendSMSCaseFive() {
        log.info("Send SMS");
    }
```


```java
    public static void caseFive() {
        Phone phone = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseFive();
        }, "mail").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendSMSCaseFive();
        }, "sms").start();
    }
// Mar 26, 2023 8:05:31 PM com.cch.juc.day02.LockDemo lambda$8
// INFO: Thread Name: mail
// Mar 26, 2023 8:05:31 PM com.cch.juc.day02.Phone sendMailCaseFive
// INFO: Send Mail
// Mar 26, 2023 8:05:32 PM com.cch.juc.day02.LockDemo main
// INFO: Thread Name: main
// Mar 26, 2023 8:05:32 PM com.cch.juc.day02.LockDemo lambda$9
// INFO: Thread Name: sms
// Mar 26, 2023 8:05:32 PM com.cch.juc.day02.Phone sendSMSCaseFive
// INFO: Send SMS
```

6. 有兩個靜態同步方法，兩部手機，先打印 phon1 的 mail 還是 phone2 的 sms

```java
   public static void caseSix() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseFive();
        }, "phone1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone2.sendSMSCaseFive();
        }, "phone2").start();
    }
// Mar 26, 2023 8:07:33 PM com.cch.juc.day02.LockDemo lambda$10
// INFO: Thread Name: phone1
// Mar 26, 2023 8:07:33 PM com.cch.juc.day02.Phone sendMailCaseFive
// INFO: Send Mail
// Mar 26, 2023 8:07:33 PM com.cch.juc.day02.LockDemo main
// INFO: Thread Name: main
// Mar 26, 2023 8:07:33 PM com.cch.juc.day02.LockDemo lambda$11
// INFO: Thread Name: phone2
// Mar 26, 2023 8:07:33 PM com.cch.juc.day02.Phone sendSMSCaseFive
// INFO: Send SMS
```

7. 有一個靜態同步方法，和一個普通同步方法，一部手機，先打印 mail 還是 sms

```java
    public static void caseSeven() {
        Phone phone = new Phone();


        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseFive();
        }, "mail").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendSMS();
        }, "sms").start();
    }
// Mar 26, 2023 8:13:30 PM com.cch.juc.day02.LockDemo lambda$12
// INFO: Thread Name: mail
// Mar 26, 2023 8:13:30 PM com.cch.juc.day02.LockDemo main
// INFO: Thread Name: main
// Mar 26, 2023 8:13:30 PM com.cch.juc.day02.LockDemo lambda$13
// INFO: Thread Name: sms
// Send SMS
// Mar 26, 2023 8:13:33 PM com.cch.juc.day02.Phone sendMailCaseFive
// INFO: Send Mail
```

8. 有一個靜態同步方法，和一個普通同步方法，兩部手機，先打印 phon1 的 mail 還是 phone2 的 sms

```java
    public static void caseEight() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseFive();
        }, "phone1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone2.sendSMS();
        }, "phone2").start();
    }
// Mar 26, 2023 8:16:37 PM com.cch.juc.day02.LockDemo lambda$14
// INFO: Thread Name: phone1
// Mar 26, 2023 8:16:37 PM com.cch.juc.day02.LockDemo main
// INFO: Thread Name: main
// Mar 26, 2023 8:16:37 PM com.cch.juc.day02.LockDemo lambda$15
// INFO: Thread Name: phone2
// Send SMS
// Mar 26, 2023 8:16:40 PM com.cch.juc.day02.Phone sendMailCaseFive
// INFO: Send Mail
```

**總結**
對於第一和二案例

- 一個物件裡如果有多個 `synchronized` 方法，**某一個時刻內，只要一個線程去掉用其中的一個 `synchronized`  方法**，其它線程只能等待。因此該時刻只有有唯一的線程去訪問這些 `synchronized` 方法，**鎖的當前對象是 `this`，被鎖定後，其它線程都不能進入當前物件的其它 `synchronized`  方法**。

對於第三和四案例

- 加個普通方法後，同步鎖與他無關
- 換成兩個不同 phone 物件後，鎖不是針對同一物件，因次也是不相關，情況立刻會有所變化(物件鎖)

對於第五和六案例，換成靜態方法
- 對於普通同步方法，**鎖的對象是當前實例**，指的是 `this`，具體的每部手機，所有的普通方法用的都是同把鎖
- 對於靜態同步方法，**鎖的對象是當前 Class 對象**，像是 `Phone.class`
- 對於同步方法塊，**鎖的對象是 `synchronized` 括號內的對象**

對於第七和八案例
- 當一個線程試圖訪問同步程式碼時它首先必須得到鎖，正常退出或拋出異常時必須釋放鎖
- 所有普通方法用的都是同一把鎖(實例對象本身)，一個實例對象的普通同步方法獲取鎖後，該實例對象的其他普通同步方法必須等待獲取鎖的方法釋放鎖後才能獲取鎖
- 靜態同步方法鎖目標是類本身，物件鎖和類鎖，是兩個不同的對象，靜態同步方法和普通同步方法是不會有競爭條件的，但是一旦一個靜態同步方法獲取鎖後，其他的靜態同步方法需等待該方法釋放鎖後才能獲取

### synchronized 鎖的是什麼
1. 為何任何物件都可以成為一個鎖呢

### monitor
Monitor 是一種程序結構，結構內有多個子程序形成的多個工作線程互斥訪問共享資源。

執行線程就要求先成功持有 Monitor，然後才能執行方法，最後當方法完成(正常或非正常完成)時釋放 Monitor。在方法執行期間，執行線程持有了 Monitor，其他任何線程都無法在獲取到同一個 Monitor。

以下面範例來說，誰先持有 `obj` 這個鎖誰就先執行。
```java
public class ShareThreadDemo {
    private static Logger log = Logger.getLogger(ShareThreadDemo.class.getName());
    static int c = 0;
    static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (obj) {
                    c++;
                }
            }
            log.info(String.format("T1 c = %d", c));
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (obj) {
                    c--;
                }
            }
            log.info(String.format("T2 c = %d", c));
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info(String.format("c = %d", c));
    }
} 
```


### 公平鎖和非公平鎖

以[範例](app/src/main/java/com/cch/juc/day02/SaleTicketDemo.java)來看，

有 SaleA、SaleB 和 SaleC 三個售票員對 50 張票進行販售。具體程式碼如下

```java
class Ticket {
    private static Logger log = Logger.getLogger(Ticket.class.getName());
    private int number = 50;
    ReentrantLock reentrantLock = new ReentrantLock(); // 非公平
    public void sale () {
        reentrantLock.lock();
        try {
            if (number > 0) {
                log.info(String.format("%s 賣出第 %d 張，還剩下票 %d 張票 ", Thread.currentThread().getName(), (number--), number));
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
```

SaleA、SaleB 和 SaleC 各表是一個執行續，預設下執行會發現，有時只有 SaleA 在販售，有時只有 SaleA  和 SaleB 在販售，這產生的一些不公平的現象。

```bash
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.SaleTicketDemo main
INFO: Thread Name: main
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleA 賣出第 50 張，還剩下票 49 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleA 賣出第 49 張，還剩下票 48 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleA 賣出第 48 張，還剩下票 47 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleA 賣出第 47 張，還剩下票 46 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleA 賣出第 46 張，還剩下票 45 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleA 賣出第 45 張，還剩下票 44 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleA 賣出第 44 張，還剩下票 43 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleA 賣出第 43 張，還剩下票 42 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleA 賣出第 42 張，還剩下票 41 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleA 賣出第 41 張，還剩下票 40 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 40 張，還剩下票 39 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 39 張，還剩下票 38 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 38 張，還剩下票 37 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 37 張，還剩下票 36 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 36 張，還剩下票 35 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 35 張，還剩下票 34 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 34 張，還剩下票 33 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 33 張，還剩下票 32 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 32 張，還剩下票 31 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 31 張，還剩下票 30 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 30 張，還剩下票 29 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 29 張，還剩下票 28 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 28 張，還剩下票 27 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 27 張，還剩下票 26 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 26 張，還剩下票 25 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 25 張，還剩下票 24 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 24 張，還剩下票 23 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 23 張，還剩下票 22 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 22 張，還剩下票 21 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 21 張，還剩下票 20 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 20 張，還剩下票 19 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 19 張，還剩下票 18 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 18 張，還剩下票 17 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 17 張，還剩下票 16 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 16 張，還剩下票 15 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 15 張，還剩下票 14 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 14 張，還剩下票 13 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 13 張，還剩下票 12 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 12 張，還剩下票 11 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 11 張，還剩下票 10 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 10 張，還剩下票 9 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 9 張，還剩下票 8 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 8 張，還剩下票 7 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 7 張，還剩下票 6 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 6 張，還剩下票 5 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 5 張，還剩下票 4 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 4 張，還剩下票 3 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 3 張，還剩下票 2 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 2 張，還剩下票 1 張票
4月 02, 2023 10:52:23 上午 com.cch.juc.day02.Ticket sale
INFO: SaleB 賣出第 1 張，還剩下票 0 張票
```

將 `ReentrantLock` 的建構值設定為 `true` 可以改善上面出現的狀況，這樣銷售員都能平均的被分配到任務。
```java
class TicketFair {
    private static Logger log = Logger.getLogger(TicketFair.class.getName());
    private int number = 50;
    ReentrantLock reentrantLock = new ReentrantLock(true);
    public void sale () {
        reentrantLock.lock();
        try {
            if (number > 0) {
                log.info(String.format("%s 賣出第 %d 張，還剩下票 %d 張票 ", Thread.currentThread().getName(), (number--), number));
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
```

**公平鎖**

多個線程按照申請鎖的順序來獲取鎖。


**非公平鎖**

多個線程獲取鎖的順序並不是按照申請鎖的順序，有可能或申請的線程比先申請鎖的線程優先獲取鎖，在高併發下，有可能造成優先級翻轉或是飢餓的狀態(一值拿不到鎖)。`ReentrantLock` 預設是此模式。

1. 為什麼有公平和非公平鎖的設計 ? 預設為什麼是非公平 ?

要把被阻塞的線程喚醒並獲取鎖是有時間差，以 CPU 來看，只要觸發 `Context Switch`，作業系統就會保存當前的線程狀態，並透過 `Program Counter Register` 恢復要執行的線程狀態。該狀態會包含程式計數器、虛擬機中每個 Stack Frame 訊息（局部變量、返回地址等）。因此頻繁切換 Context Switch 會有性能的影響。對於*非公平鎖*能夠更充分利用 CPU 的時間切片，盡量不讓 CPU 閒置。

當一個線程請求獲取鎖同步狀態，然後釋放同步狀態，所以剛釋放鎖的線程在此刻再次獲取同步狀態機率變大非常大，所以減少了線程開銷。

2. 使用時機

為了高吞吐量，非公平鎖是首要選擇，因為在 CPU 開銷上會有比較好的效果。否則可選用公平鎖。

### 可重入鎖(遞歸鎖)
在*同一個線程*在外層方法獲取鎖的時候，再進入該線程的內層方法會自動獲取鎖(鎖的對象是同一對象)，不會因為之前已經獲取過還沒釋放而被阻塞，也就是不會導致死鎖。

對於 JAVA 中的 `synchronized` 和 `ReentrantLock` 都是可重入鎖。

類型上可分為

#### 隱式鎖
`synchronized` 是一個例子。在一個 `synchronized` 修飾的方法或程式碼塊的內部調用本類別的其他 `synchronized` 修飾的方法或程式碼塊時，*是可以永遠得到鎖的*

同步程式碼塊
```java
    private static void reEntryImplicit() {
        final Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                log.info(String.format("%s, 外層", Thread.currentThread().getName()));
                synchronized (o) {
                    log.info(String.format("%s, 中層", Thread.currentThread().getName()));
                    synchronized (o) {
                        log.info(String.format("%s, 內層", Thread.currentThread().getName()));
                    }
                }
            }
        }, "implicit").start();
    }
```


同步方法

```java
    public synchronized void m1() {
        log.info(String.format("%s, m1 come in...", Thread.currentThread().getName()));
        m2();
        log.info("end...");
    }

    public synchronized void m2() {
        log.info(String.format("%s, m2 come in...", Thread.currentThread().getName()));
        m3();
    }

    public synchronized void m3() {
        log.info(String.format("%s, m3 come in...", Thread.currentThread().getName()));
    }

    private static void reEntryImplicitMethodBlock() {
        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();
        new Thread(() -> {
            reEntryLockDemo.m1();
        }, "reEntryImplicitMethodBlock").start();
    }

// 4月 02, 2023 12:16:00 下午 com.cch.juc.day02.ReEntryLockDemo m1
// INFO: reEntryImplicitMethodBlock, m1 come in...
// 4月 02, 2023 12:16:00 下午 com.cch.juc.day02.ReEntryLockDemo m2
// INFO: reEntryImplicitMethodBlock, m2 come in...
// 4月 02, 2023 12:16:00 下午 com.cch.juc.day02.ReEntryLockDemo m3
// INFO: reEntryImplicitMethodBlock, m3 come in...
// 4月 02, 2023 12:16:00 下午 com.cch.juc.day02.ReEntryLockDemo m1
// INFO: end...
```

每個鎖對象擁有一個計數器和一個持有該鎖的線程的指針。

當執行 monitorente 時(加鎖)，如果目標鎖對象的計數器為 0，那表示沒有被其他線程所持有，java 虛擬機會將該鎖對象的持有線程設置為當前線程，並將其計數器加 1。

在目標鎖對象的計數器不為 0 強況下，如果鎖對象的持有線程式當前線程，那麼 JAVA 虛擬機可以將其計數器加 1，否則需要等待，直到持有該線程釋放該鎖。

當執行 monitorexit (解鎖)時，java 虛擬機將鎖對象計數器減 1，如果為 0 表示釋放。
#### 顯示鎖

`ReentrantLock` 是一個例子。要手動進行 `lock` 和 `unlock`，`lock` 幾次就要 `unlock` 幾次。

對於多個線程，只要出現不成對的 `lock` 那將導致阻塞甚至死鎖。
```java
    private static void reEntryShowMethod() {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                log.info(String.format("%s, 外層", Thread.currentThread().getName()));
                lock.lock();
                try {
                    log.info(String.format("%s, 內層", Thread.currentThread().getName()));
                } finally{
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();


        new Thread(() -> {
            lock.lock();
            try {
                log.info(String.format("%s, T2 外層", Thread.currentThread().getName()));
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
```

### 死鎖與排查

死鎖指兩個或以上的線程在執行過程中，因爭多資源而造成互相等待的現象，若無外力介入那將無法繼續往下執行，如果資源是充足，而行程請求都能夠得到滿足，死鎖出現就會低的多，否則資源將會競爭進而導致死鎖。

```java
public class DeadlockDemo {
    private static Logger log = Logger.getLogger(DeadlockDemo.class.getName());
    private static Object A = new Object(); // 鎖 A
    private static Object B = new Object(); // 鎖 B
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                log.info("Lock A");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                synchronized (B) {
                    log.info("Lock B");
                    log.info("Open...");
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (B) {
                log.info("Lock B");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                synchronized (A) {
                    log.info("Lock A");
                    log.info("Open...");
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}

```

排查
```bash
>jps -l
14768 com.cch.juc.day03.DeadlockDemo
26192 org.eclipse.lsp4mp.ls.MicroProfileServerLauncher
12612 c:\Users\gama7\.vscode\extensions\vscjava.vscode-gradle-3.12.7\lib\gradle-language-server.jar
22136 c:\Users\gama7\.vscode\extensions\sonarsource.sonarlint-vscode-3.16.0-win32-x64\server\sonarlint-ls.jar
30504 jdk.jcmd/sun.tools.jps.Jps
27036 c:\Users\gama7\.vscode\extensions\redhat.java-1.16.0-win32-x64\server\plugins\org.eclipse.equinox.launcher_1.6.400.v20210924-0641.jar
4396 com.github.badsyntax.gradle.GradleServer
```

```bash
>jstack 14768
2023-04-02 17:02:00
Full thread dump Java HotSpot(TM) 64-Bit Server VM (11.0.13+10-LTS-370 mixed mode):

Threads class SMR info:
_java_thread_list=0x00000221cf830b30, length=12, elements={
0x00000221cf5b9000, 0x00000221cf5bc800, 0x00000221cf62b800, 0x00000221cf62e000,
0x00000221cf62f000, 0x00000221cf630800, 0x00000221cf637800, 0x00000221cf638800,
0x00000221cf7cf800, 0x00000221cfac0800, 0x00000221cfac1000, 0x00000221a2423000
}

"Reference Handler" #2 daemon prio=10 os_prio=2 cpu=0.00ms elapsed=75.07s tid=0x00000221cf5b9000 nid=0x4de4 waiting on condition  [0x00000077739fe000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@11.0.13/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@11.0.13/Reference.java:241)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@11.0.13/Reference.java:213)

"Finalizer" #3 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=75.07s tid=0x00000221cf5bc800 nid=0x3fe8 in Object.wait()  [0x0000007773aff000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.13/Native Method)
        - waiting on <0x0000000621c08fa8> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.13/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x0000000621c08fa8> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.13/ReferenceQueue.java:176)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@11.0.13/Finalizer.java:170)

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=75.05s tid=0x00000221cf62b800 nid=0x5724 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Attach Listener" #5 daemon prio=5 os_prio=2 cpu=0.00ms elapsed=75.05s tid=0x00000221cf62e000 nid=0x3724 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Service Thread" #6 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=75.05s tid=0x00000221cf62f000 nid=0x5f8c runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #7 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=75.05s tid=0x00000221cf630800 nid=0x5150 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"C1 CompilerThread0" #10 daemon prio=9 os_prio=2 cpu=15.63ms elapsed=75.05s tid=0x00000221cf637800 nid=0x4a08 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"Sweeper thread" #11 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=75.05s tid=0x00000221cf638800 nid=0x4bd4 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Common-Cleaner" #12 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=75.03s tid=0x00000221cf7cf800 nid=0x442c in Object.wait()  [0x00000077742fe000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.13/Native Method)
        - waiting on <0x0000000621d57198> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.13/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x0000000621d57198> (a java.lang.ref.ReferenceQueue$Lock)
        at jdk.internal.ref.CleanerImpl.run(java.base@11.0.13/CleanerImpl.java:148)
        at java.lang.Thread.run(java.base@11.0.13/Thread.java:834)
        at jdk.internal.misc.InnocuousThread.run(java.base@11.0.13/InnocuousThread.java:134)

"t1" #14 prio=5 os_prio=0 cpu=0.00ms elapsed=74.97s tid=0x00000221cfac0800 nid=0x507c waiting for monitor entry  [0x00000077743ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.cch.juc.day03.DeadlockDemo.lambda$0(DeadlockDemo.java:20)
        - waiting to lock <0x0000000621ae0dc0> (a java.lang.Object)
        - locked <0x0000000621ae0db0> (a java.lang.Object)
        at com.cch.juc.day03.DeadlockDemo$$Lambda$8/0x0000000800062c40.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.13/Thread.java:834)

"t2" #15 prio=5 os_prio=0 cpu=0.00ms elapsed=74.97s tid=0x00000221cfac1000 nid=0xf3c waiting for monitor entry  [0x00000077744ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.cch.juc.day03.DeadlockDemo.lambda$1(DeadlockDemo.java:35)
        - waiting to lock <0x0000000621ae0db0> (a java.lang.Object)
        - locked <0x0000000621ae0dc0> (a java.lang.Object)
        at com.cch.juc.day03.DeadlockDemo$$Lambda$9/0x0000000800098040.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.13/Thread.java:834)

"DestroyJavaVM" #16 prio=5 os_prio=0 cpu=31.25ms elapsed=74.97s tid=0x00000221a2423000 nid=0x2600 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"VM Thread" os_prio=2 cpu=0.00ms elapsed=75.07s tid=0x00000221cf596800 nid=0x4034 runnable

"GC Thread#0" os_prio=2 cpu=0.00ms elapsed=75.09s tid=0x00000221a2439000 nid=0x1300 runnable

"G1 Main Marker" os_prio=2 cpu=0.00ms elapsed=75.09s tid=0x00000221a24b8800 nid=0x23ac runnable

"G1 Conc#0" os_prio=2 cpu=0.00ms elapsed=75.09s tid=0x00000221a24bb800 nid=0x7180 runnable

"G1 Refine#0" os_prio=2 cpu=0.00ms elapsed=75.08s tid=0x00000221cebfb000 nid=0x7ba8 runnable

"G1 Young RemSet Sampling" os_prio=2 cpu=0.00ms elapsed=75.08s tid=0x00000221cebfe000 nid=0x19cc runnable
"VM Periodic Task Thread" os_prio=2 cpu=0.00ms elapsed=75.03s tid=0x00000221cf7ce800 nid=0x6274 waiting on condition

JNI global refs: 9, weak refs: 0


Found one Java-level deadlock:
=============================
"t1":
  waiting to lock monitor 0x00000221cf5c3200 (object 0x0000000621ae0dc0, a java.lang.Object),
  which is held by "t2"
"t2":
  waiting to lock monitor 0x00000221cf5c5100 (object 0x0000000621ae0db0, a java.lang.Object),
  which is held by "t1"

Java stack information for the threads listed above:
===================================================
"t1":
        at com.cch.juc.day03.DeadlockDemo.lambda$0(DeadlockDemo.java:20)
        - waiting to lock <0x0000000621ae0dc0> (a java.lang.Object)
        - locked <0x0000000621ae0db0> (a java.lang.Object)
        at com.cch.juc.day03.DeadlockDemo$$Lambda$8/0x0000000800062c40.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.13/Thread.java:834)
"t2":
        at com.cch.juc.day03.DeadlockDemo.lambda$1(DeadlockDemo.java:35)
        - waiting to lock <0x0000000621ae0db0> (a java.lang.Object)
        - locked <0x0000000621ae0dc0> (a java.lang.Object)
        at com.cch.juc.day03.DeadlockDemo$$Lambda$9/0x0000000800098040.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.13/Thread.java:834)

Found 1 deadlock.
```

或是使用 `jconsole`

## LockSupport 與線程中斷

### 中斷機制
一個線程不應該由其他線程來強制中斷或是停止，而是應該由**線程自己自行停止**。

中斷只是一種協商機制，java 沒有給中斷增加任何語法，中斷的過程完全需要程序員自己實作。因此要中斷一個線程，需要手動調用 `interrupt` 方法，**該方法也僅僅是將線程對象的中段標示設置為 `true`**，在程式碼中就要不斷的檢測當前線程是是否被標示為 true 被中斷狀態，這過程中可被別的線程調用，或是自己本身調用。

**中斷 API**

|方法名|static|功能說明|注意|
|---|---|---|---|
|Boolean isInterrupted()||判斷是否被打斷|不會清除打斷標記|
|void interrupt()||打斷線程|如果被打斷線程正在 sleep、wait、join 會導致被打斷的線程拋出 `InterruptedException`，並*清除打斷標記*；如果打斷的正在運行的線程，則會*設置打斷標記*，`park` 的線程被打斷，也會*設置打斷標記*|
|interrupted()|static|判斷當前線程是否被打斷|會清除打斷標記，重新被設計為 `false`|

### 如何停止中斷運行中的線程

**volatile**
下面範例透過 `volatile` 可見性，透過標誌進行不同線程的中斷協商。
```java
public class InterruptVolatitleDemo {
    private static Logger log = Logger.getLogger(InterruptVolatitleDemo.class.getName());

    static volatile boolean isStop = false;
    private static void volatileDemo() throws InterruptedException {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    log.info(String.format("%s, isStop: %s", Thread.currentThread().getName(), isStop));
                    break;
                }
                log.info("t1, is volatile");
            }
        }, "t1").start();
        Thread.sleep(200);
        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}
```

**AtomicBoolean**

```java
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private static void atomicDemo() throws InterruptedException {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    log.info(String.format("%s, isStop: %s", Thread.currentThread().getName(), atomicBoolean.get()));
                    break;
                }
                log.info("t1, is atomicBoolean");
            }
        }, "t1").start();
        Thread.sleep(200);
        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }
```

**Thread 中中斷 API 方法**

```java
    private static void threadApiDemo() throws InterruptedException {
        Thread t1 = new Thread(() -> {
                    while (true) {
                        if (Thread.currentThread().isInterrupted()) {
                            log.info(String.format("%s, isStop: %s", Thread.currentThread().getName(), Thread.currentThread().isInterrupted()));
                            break;
                        }
                        log.info("t1, is interrupt api.");
                    }
            }, "t1");
        t1.start();
        Thread.sleep(150);
        new Thread(() -> {
            t1.interrupt(); // 設置為 true
            log.info(String.format("%s, Mark t1: %s", Thread.currentThread().getName(), t1.isInterrupted()));
        }, "t2").start();
        // 或是自己中斷
        t1.interrupt();
    }
```

線程處於正常活動狀態，那麼會將該線程的中段標誌設置為 true，僅此而已。被設置中斷的線程將繼續運行，不受影響。對於 `interrupt` 並不能真正中斷線程，需要被調用的線程自己進行配合才行。

下面範例基本上體現出了，`interrupt` 設置並非被中斷

```java
   private static void interrupteisPauseDemo() throws InterruptedException {
        Thread t1 = new Thread(() -> {
                    for (int i = 0; i < 10; i++) {
                        log.info(String.format("i: %d", i));
                    }
                    log.info(String.format("called interrupt() flag: %s", Thread.currentThread().isInterrupted()));
                }, "t1");
        t1.start();

        log.info(String.format("t1 default interrupt flag: %s", t1.isInterrupted()));

        Thread.sleep(2);

        t1.interrupt();
        log.info(String.format("%s called interrupt() flag: %s", Thread.currentThread().getName() ,t1.isInterrupted()));

        Thread.sleep(2000);
        log.info("Wating 2000 millis");
        log.info(String.format("%s called interrupt() 之後的 flag: %s", Thread.currentThread().getName() ,t1.isInterrupted()));
    }

// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo interrupteisPauseDemo
// INFO: t1 default interrupt flag: false
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo lambda$6
// INFO: i: 0
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo lambda$6
// INFO: i: 1
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo lambda$6
// INFO: i: 2
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo lambda$6
// INFO: i: 3
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo interrupteisPauseDemo
// INFO: main called interrupt() flag: true
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo lambda$6
// INFO: i: 4
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo lambda$6
// INFO: i: 5
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo lambda$6
// INFO: i: 6
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo lambda$6
// INFO: i: 7
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo lambda$6
// INFO: i: 8
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo lambda$6
// INFO: i: 9
// 4月 02, 2023 9:51:05 下午 com.cch.juc.day03.InterruptDemo lambda$6
// INFO: called interrupt() flag: true
// 4月 02, 2023 9:51:07 下午 com.cch.juc.day03.InterruptDemo interrupteisPauseDemo
// INFO: Wating 2000 millis
// 4月 02, 2023 9:51:07 下午 com.cch.juc.day03.InterruptDemo interrupteisPauseDemo
// INFO: main called interrupt() 之後的 flag: false
```

下面範例，驗證被打斷線程正在 sleep、wait、join 會導致被打斷的線程拋出 `InterruptedException`，並*清除打斷標記*
```java
public class InterruptDemo2 {
    private static Logger log = Logger.getLogger(InterruptDemo2.class.getName());
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if(Thread.currentThread().isInterrupted()) {
                    log.info(String.format("%s, interrupt flag: %s", Thread.currentThread().getName(), Thread.currentThread().isInterrupted()));
                    log.info("Terminating");
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // 因為被重新設置為 false，因此需要再呼叫一次
                    e.printStackTrace();
                }
                log.info("Hello...");
            }
        }, "t1");
        t1.start();

        Thread.sleep(1000);

        new Thread(() -> {
            t1.interrupt();
        }, "t2").start();
    }
}
// 4月 02, 2023 10:07:33 下午 com.cch.juc.day03.InterruptDemo2 lambda$0
// INFO: Hello...
// 4月 02, 2023 10:07:33 下午 com.cch.juc.day03.InterruptDemo2 lambda$0
// INFO: Hello...
// 4月 02, 2023 10:07:34 下午 com.cch.juc.day03.InterruptDemo2 lambda$0
// INFO: Hello...
// 4月 02, 2023 10:07:34 下午 com.cch.juc.day03.InterruptDemo2 lambda$0
// INFO: Hello...
// java.lang.InterruptedException: sleep interrupted
//         at java.base/java.lang.Thread.sleep(Native Method)
//         at com.cch.juc.day03.InterruptDemo2.lambda$0(InterruptDemo2.java:16)
//         at java.base/java.lang.Thread.run(Thread.java:834)
// 4月 02, 2023 10:07:34 下午 com.cch.juc.day03.InterruptDemo2 lambda$0
// INFO: Hello...
// 4月 02, 2023 10:07:34 下午 com.cch.juc.day03.InterruptDemo2 lambda$0
// INFO: t1, interrupt flag: true
// 4月 02, 2023 10:07:34 下午 com.cch.juc.day03.InterruptDemo2 lambda$0
// INFO: Terminating
```

### LockSupport 是什麼

**等待喚醒機制**
1. `Object` 類中的 `wait()` 讓線程等待，使用 `notify()` 喚醒
2. JUC 中 `Condition` 的 `await()` 讓線程等待，使用 `signal()` 喚醒
3. `LockSupport` 類可以阻塞當前線程以及喚醒指定被阻塞的線程
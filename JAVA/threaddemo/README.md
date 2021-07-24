## 行程與線程

### 行程

- 應用程式的實例，負責加載指令、管理記憶體或 IO 
- 該實例是從硬碟加載至記憶體

### 線程
- 在行程內可分有一到多個線程
- 一個線程就是一個指令流，該指令流會有順序的交給 CPU 執行
- 在 JAVA 中，線程是最小調度單位，行程會作為資源調度的最小單位並提供資源給線程共享使用
- 一個進程同一時間並行執行個執行續，就是多執行續
- 執行續做為調度和執行續單位，每個現成擁有獨立的運行 stack 和程式計數器（pc），執行續的開銷較小
    - 執行續共享記憶體資源（Method Area、Heap）

在行程間的通訊中在同一台運算電腦上其通訊方式稱為 IPC；在不同運算電腦上可能是 HTTP 或是其他協定。線程的優勢在於 context switch 成本比行程低。

### Parallel 與 Concurrent 概念
單核 CPU 下，線程實際上是*串行執行*。然而這些 CPU 要執行什麼線程都是由作業系統上的任務調度去執行，也因為 CPU 切換很快所以感覺會是同時運行。

Cocurrent 是同一時間處裡多個事情，通常會是以輪流方式使用 CPU。如下圖

![](https://i.imgur.com/xhCNUQC.png)



在多核環境下，同一時間每個 Core 都可以執行不同的執行續內容，這就是 Parallel

![](https://i.imgur.com/86RyWm0.png)


>Concurrent 是同一時間應對多件事情的能力
>Parallel 是同一時間動手做多件事情的能力

### 應用
從方法調用角度看，如果
- 需要等待返回結果，才能繼續運行就是*同步*
- 不需要等待返回結果，就能繼續運行就是*異步*

1. 設計

多線程可以讓方法執行變為異步的，也就是不要讓費時間的概念，讀取硬碟要花費 5 秒，假設不使用線程調度則會產生浪費那 5 秒的時間。

2. 結果

- tomcat 的異步 servlet 也是類似該目的，讓使用者線程處理耗時較長的操作，避免阻塞主線程


同步[範例](src\main\java\com\example\cch\day01\Sync.java)

從輸出來看是串行執行，由上至下執行

```bash
2021-07-03 20:58:15 INFO  [main] c.Sync - Start....  
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello  
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello  
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Thread Hello
2021-07-03 20:58:15 INFO  [main] c.Sync - Read Success! Cost: 41.0  
2021-07-03 20:58:15 DEBUG [main] c.Sync - do other things ....
```

異步[範例](src\main\java\com\example\cch\day01\Async.java)

從輸出來看，`do other things` 並沒有等待該執行續執行完成才執行。我們這邊可以看見兩個不同執行續的交互。

```shell=
2021-07-03 20:57:14 INFO  [main] c.Async - Start...  
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello  
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [main] c.Async - Read Success! Cost: 6.0
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 DEBUG [main] c.Async - do other things ....
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
2021-07-03 20:57:14 INFO  [Thread-0] c.Async - Thread Hello
```

> IO 不占用 CPU，但是會等待 IO 結束，因此才有非阻塞 IO 和異步 IO 優化

## JAVA Thread
### 創建和運行

1. 使用 Thread

```java
// 創建
        Thread t = new Thread("t1"){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                // Create Task
                log.debug("Hello");
            }
        };
// 執行
        t.start();
// 2021-07-03 21:32:08 DEBUG [t1] c.ThreadTest - Hello  
// 2021-07-03 21:32:08 DEBUG [main] c.ThreadTest - Main Running
```



2. Runnable 和 Thread

將線程(Thread)和執行任務(Runnable)分開，[範例](\src\main\java\com\example\cch\day02\RunnableTest.java)

輸出
```
2021-07-03 21:35:54 DEBUG [main] c.RunnableTest - Main ...  
2021-07-03 21:35:54 DEBUG [t2] c.RunnableTest - Runnable...
```



3. lambda 


```java
@Slf4j(topic = "c.LambdaTest")
public class LambdaTest {
    public static void main(String[] args) {
        Runnable r = () -> log.debug("Lambda...");
        Thread t = new Thread(r, "t3");
        t.start();
    }
}
```

或者

```java
Thread t = new Thread(() -> {log.debug("Lambda");}, "t4");
t.start();
```

>Thread 與 Runnable 關係
>- Runnable 更容易和高等級 API 配合
>- Runnable 脫離 Thread 繼層體系會更靈活

4. FutureTask 配合 Thread

FutureTask 可以接收 Callable 類型參數，用來處裡有返回結果的情況，[範例](src\main\java\com\example\cch\day02\FutureTaskTest.java)

輸出
```
2021-07-03 21:54:44 DEBUG [t5] c.FutureTaskTest - running...  
2021-07-03 21:54:45 DEBUG [main] c.FutureTaskTest - Get: 100
```
## 原理之線程運行

### stack 與  stack frame 
`JVM` 由堆、棧、方法區所組成，其中線程就是儲存在棧中。每個線程啟動後，虛擬機就會分配一塊棧記憶體
- 每個 stack 由多個 `stack frame` 組成，對應著每次方法調用十所佔用內存
- 每個線程只能有一個活動 `stack frame`，對應著當前正在執行的那個方法
>每個線程都有自己的 `stack` 而互不干擾

### Thread Context Switch

可能因某種原因導致當前 CPU 步在執行當前的線程，轉而執行另一個線程。原因可能是
- 線程的 CPU 時間片使用完了
- 垃圾回收
- 有更高優先級的線程需要處裡
- 線程自行調用 `sleep`、`yield`、`wait`、`join`、`park`、`synchronized`、`lock` 等方法

只要觸發 `Context Switch`，作業系統就會保存當前的線程狀態，並透過 `Program Counter Register` 恢復要執行的線程狀態。該狀態會包含程式計數器、虛擬機中每個 `Stack Frame` 訊息（局部變量、返回地址等）。頻繁切換 `Context Switch` 會有性能的影響。

## Thread 的常見方法

|方法名|static|功能說明|注意|
|---|---|---|---|
|start()||啟動一個新線程，在新線程中運行 `run()` 中的代碼| `start()` 只是讓線程進入*就緒狀態*，裡面代碼不一定立刻運行，只有當 CPU 將時間片分給線程時，才能進入運行狀態，執行代碼。每個線程的 `start()` 只能調用一次，調用多次就會出現`IllegalThreadStateException` |
|run()||新線程啟動會調用的方法|如果在構造 `Thread` 物件時傳遞了`Runnable` 參數，則線程啟動後會調用 `Runnable` 中的 `run()`，否則默認不執行任何操作。但可以創建 `Thread` 的子類物件，來覆蓋默認行為|
|join()||等待線程運行結束||
|join(long n)||等待線程運行結束,最多等待 n 毫秒||
|getId()||獲取線程的 ID |ID 唯一|
|getName()||獲取線程名稱||
|setName(String)||修改線程名稱||
|getPriority()||獲取線程優先級||
|setPriority(int)||修改線程優先級|java 中規定線程優先級是 1~10 的整數，較大的優先級能提高該線程被 CPU 調度的機率|
|getState()||獲取線程狀態|Java 中線程狀態是用 6 個 enum 表示，分別為：NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED|
|isInterrupted()||判斷是否被打斷|不會清除打斷標記|
|isAlive()||線程是否存活（還沒有運行完畢）||
|interrupt()||打斷線程|如果被打斷線程正在 sleep、wait、join 會導致被打斷的線程拋出 `InterruptedException`，並清除打斷標記；如果打斷的正在運行的線程，則會設置打斷標記，`park` 的線程被打斷，也會設置打斷標記|
|interrupted()|static|判斷當前線程是否被打斷|會清除打斷標記|
|currentThread()|static|獲取當前正在執行的線程	|
|sleep(long n)|static|讓當前執行的線程休眠 n 毫秒，休眠時讓出 cpu 的時間片給其它線程|| 
|yield()|static|提示線程調度器讓出當前線程對CPU的使用|主要是為了測試和調試|


### Start 與 Run

Run 方式
```java
        Thread t = new Thread(() -> log.debug("Running..."),"t1");

        t.run();

        log.debug("Main....");
        
// 2021-07-04 13:24:44 DEBUG [main] c.StartAndRun - Running...  
// 2021-07-04 13:24:44 DEBUG [main] c.StartAndRun - Main....  
```

Start 方式

```java

        Thread t = new Thread(() -> log.debug("Running..."),"t1");

        t.start();

        log.debug("Main....");

// 2021-07-04 13:25:21 DEBUG [t1] c.StartAndRun - Running...  
// 2021-07-04 13:25:21 DEBUG [main] c.StartAndRun - Main.... 
```

兩者差異可以知道說 `run` 其實是運行在 main Thread 上，所以不會有 `start` 運行的優勢。我們可透過 `getState()` 來查看線程的狀態。

### Sleep 與 Yield
##### Sleep
- 調用 `sleep` 會讓當前線程從 `running` 狀態變成 `Timed Waiting` 狀態
- 其它線程可使用 `interrupt` 方法打斷正在休眠的線程，這時 `sleep` 方法會拋出 `InterruptedException`
- 睡眠結束後的線程未必立刻會執行
    - 與作業系統中的任務調度有關

```java
@Slf4j(topic = "c.SleepTest")
public class SleepTest {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000); // t Thread 休眠
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        },"t3");

        log.debug("1 - t3 State: {}", t.getState());
        t.start();

        log.debug("2 - t3 State: {}", t.getState());

        try {
            Thread.sleep(1000); // Main Thread 休眠
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        log.debug("3 - t3 State: {}", t.getState());
    }

}
// 2021-07-04 13:36:09 DEBUG [main] c.SleepTest - 1 - t3 State: NEW  
// 2021-07-04 13:36:09 DEBUG [main] c.SleepTest - 2 - t3 State: RUNNABLE  
// 2021-07-04 13:36:12 DEBUG [main] c.SleepTest - 3 - t3 State: TIMED_WAITING  
```


interrupt 範例

```java
@Slf4j(topic = "c.InterrupterTest")
public class InterrupterTest {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            log.debug("Sleeping...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                log.debug("WAKE UP...");
                e.printStackTrace();
            }
        },"t3");

        
        t.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.debug("Interrupt...");
        t.interrupt();
    }
}

// 2021-07-04 13:44:58 DEBUG [t3] c.InterrupterTest - Sleeping...  
// 2021-07-04 13:44:59 DEBUG [main] c.InterrupterTest - Interrupt...  
// 2021-07-04 13:44:59 DEBUG [t3] c.InterrupterTest - WAKE UP...  
// java.lang.InterruptedException: sleep interrupted
//         at java.base/java.lang.Thread.sleep(Native Method)
//         at com.example.cch.day03.InterrupterTest.lambda$0(InterrupterTest.java:11)
//         at java.base/java.lang.Thread.run(Thread.java:834)
```
##### yield
- 調用 yield 會讓當前線程從 `Running` 進入 `Runnable` 就緒狀態，然後調度執行其他線程
- 具體的實現依賴於作業系統任務調度器

##### 線程優先級
- 線程優先級會提示調度器優先調度該線程，但它的影響程度不是很大，作業系統調度器可以忽略它
- 如果 CPU 較忙，那麼優先級高的線程會獲得更多的時間片，但 CPU 閒時，優先級幾乎沒作用


### Join

```java
@Slf4j(topic = "c.JoinTest")
public class JoinTest {
    static int r = 100;

    private static void test() {
        log.debug("Starting...");
        Thread t1 = new Thread(() -> {
            log.debug("T1 Starting...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            log.debug("T1 Ending...");
            r = 1;
        }, "t1");
        t1.start();
        log.debug("r = {}", r);
        log.debug("Ending...");
    }
    public static void main(String[] args) {
        test();
    }
}
// 2021-07-07 18:54:55 DEBUG [main] c.JoinTest - Starting...
// 2021-07-07 18:54:55 DEBUG [t1] c.JoinTest - T1 Starting...
// 2021-07-07 18:54:55 DEBUG [main] c.JoinTest - r = 100
// 2021-07-07 18:54:55 DEBUG [main] c.JoinTest - Ending...
// 2021-07-07 18:54:56 DEBUG [t1] c.JoinTest - T1 Ending...
```
從上面程式碼來看，r 最終結果會是 100。main 線程同時啟動 t1 線程，是以並行方式在執行，t1 線程需要 1 秒後才能將 r 變成 1；而 main 線程一開始就要打印 r 的結果。而解決方式就是使用 `join()`。

```java
    private static void test() throws InterruptedException {
        log.debug("Starting...");
        Thread t1 = new Thread(() -> {
            log.debug("T1 Starting...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            log.debug("T1 Ending...");
            r = 1;
        }, "t1");
        t1.start();
        t1.join(); // 等待執行續 t1 執行完後，main 執行續在往下執行
        log.debug("r = {}", r);
        log.debug("Ending...");
    }

// 2021-07-07 19:02:16 DEBUG [main] c.JoinTest - Starting...  
// 2021-07-07 19:02:16 DEBUG [t1] c.JoinTest - T1 Starting...  
// 2021-07-07 19:02:17 DEBUG [t1] c.JoinTest - T1 Ending...  
// 2021-07-07 19:02:17 DEBUG [main] c.JoinTest - r = 1      
// 2021-07-07 19:02:17 DEBUG [main] c.JoinTest - Ending... 
```

上面範例以調用方角度來看
- 需要等待結果返回，才能繼續運行就是同步
- 不需要等待返回結果，就能繼續運行就是異步

![](https://i.imgur.com/zQCZI1c.png)

##### 有時效的 Join

```java
public class JoinTest {
    static int r = 100;
    static int r2 = 200;

    private static void test1() throws InterruptedException {
        log.debug("Starting...");
        Thread t1 = new Thread(() -> {
            log.debug("T1 Starting...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            log.debug("T1 Ending...");
            r = 1;
        }, "t1");
        long start = System.currentTimeMillis();
        t1.start();
        t1.join(1500);// 執行續結束會導致 join 結束
        long end = System.currentTimeMillis();
        log.debug("r1 = {}, r2 = {}, cost: {}", r, r2, end-start);
        log.debug("Ending...");
    }
    public static void main(String[] args) throws InterruptedException {
        test1();
    }
}

// 2021-07-07 19:35:26:378 DEBUG [main] c.JoinTest - Starting...  
// 2021-07-07 19:35:26:381 DEBUG [t1] c.JoinTest - T1 Starting...  
// 2021-07-07 19:35:27:382 DEBUG [t1] c.JoinTest - T1 Ending...  
// 2021-07-07 19:35:27:383 DEBUG [main] c.JoinTest - r1 = 1, r2 = 200, cost: 1003  
// 2021-07-07 19:35:27:387 DEBUG [main] c.JoinTest - Ending...
```

> join 中定義的秒數如果小於 sleep 定義的秒數則 join 秒數到就會執行，否則就是 sleep 秒數結束執行。


### interrupt 方法

##### 打斷 sleep、wait、join 執行續

通常會進入阻塞狀態。以 Sleep 為例

```java
@Slf4j(topic = "c.InterrupterSleepTest")
public class InterrupterSleepTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("Sleeping...");
            try {
                Thread.sleep(5000); // sleep、wait、join 被打斷後都會把標記清空設置為 false
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        log.debug("Interrupt...");
        t1.interrupt();

        log.debug("Interrupt Mark: {}", t1.isInterrupted()); // 可用來判斷是否繼續運行或是終止
    }
}

// 2021-07-08 10:34:42:974 DEBUG [t1] c.InterrupterSleepTest - Sleeping...  
// 2021-07-08 10:34:43:985 DEBUG [main] c.InterrupterSleepTest - Interrupt...  
// java.lang.InterruptedException: sleep interrupted
//         at java.base/java.lang.Thread.sleep(Native Method)
//         at com.example.cch.day03.InterrupterSleepTest.lambda$0(InterrupterSleepTest.java:11)
//         at java.base/java.lang.Thread.run(Thread.java:834)
// 2021-07-08 10:34:43:986 DEBUG [main] c.InterrupterSleepTest - Interrupt Mark: false
```

>sleep、wait、join 被打斷後都會把標記清空設置為 false

##### 打斷正常運行的執行續

使用 `Thread.currentThread().isInterrupted()` 方式以優雅型態去終止執行續
```java
@Slf4j(topic = "c.InterrupterThreadTest")
public class InterrupterThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(true) {
               Boolean isInterrupter =  Thread.currentThread().isInterrupted();// 獲取當前執行續的打斷標記
               if(isInterrupter){
                   log.debug("Exit...");
                   break;
               }
            }
        }, "t1");

        t1.start();

        Thread.sleep(1000);
        log.debug("interrupt...");

        t1.interrupt();
    }
}
// 2021-07-08 10:42:23:940 DEBUG [main] c.InterrupterThreadTest - interrupt...  
// 2021-07-08 10:42:23:944 DEBUG [t1] c.InterrupterThreadTest - Exit...
```

> 這範例中 interrupt() 並沒有終止該線程，而是給線程自行決定

##### Two Phase Termination
在一個執行續 T1 中如何優雅（給 T2 處裡後續的機會）終止執行續 T2 ?

- 如果使用 `stop()` 會直接殺死執行續，如果該執行續鎖住了共享的資源，那被殺死後再也無法釋放該鎖，其他執行續也就無法獲取該鎖
- 使用 `System.exit(int)`，僅僅是停止一個執行續，但這種方式會讓整個程序都被停止

範例架構如下：

使用 Sleep 進行監控，但在 Sleep 中被打斷 interrupt 標記會是 false，並拋出異常，這時需要手動進行設置。

![](https://i.imgur.com/XrFVZgB.png)

```java
@Slf4j(topic = "c.TwoPhaseTermination")
public class TwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTerminate t = new TwoPhaseTerminate();
        log.debug("TwoPhaseTerminate Test Start ....");
        t.start();

        Thread.sleep(3500);

        t.stop();
    }
}

@Slf4j(topic = "c.TwoPhaseTerminate")
class TwoPhaseTerminate {
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while(true) {
                Thread currentThread = Thread.currentThread();
                if (currentThread.isInterrupted()) {
                    log.info("Exist... 優雅的");
                    break;
                }

                try {
                    Thread.sleep(1000); // case 1
                    log.info("monitor..."); // case 2
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    currentThread.interrupt(); // 重新設置標記
                }
            }
        }, "t1");

        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}
// 2021-07-08 13:48:32:524 DEBUG [main] c.TwoPhaseTermination - TwoPhaseTerminate Test Start ....  
// 2021-07-08 13:48:33:528 INFO  [t1] c.TwoPhaseTerminate - monitor...  
// 2021-07-08 13:48:34:548 INFO  [t1] c.TwoPhaseTerminate - monitor...  
// 2021-07-08 13:48:35:557 INFO  [t1] c.TwoPhaseTerminate - monitor...  
// java.lang.InterruptedException: sleep interrupted
//         at java.base/java.lang.Thread.sleep(Native Method)
//         at com.example.cch.day03.TwoPhaseTerminate.lambda$0(TwoPhaseTermination.java:32)
//         at java.base/java.lang.Thread.run(Thread.java:834)
// 2021-07-08 13:48:36:046 INFO  [t1] c.TwoPhaseTerminate - Exist... 優雅的
```

假設 `currentThread.interrupt();` 不設置會發現說他會繼續執行該執行續，這會發生在 case 1 上，而 case 2 的話則是被打斷並標記。不過並非只有這樣，後續會繼續改進。

>調用靜態的 interrupted() 會*清除標記*，它與 `isInterrupted()` 類似差別在於是否會重置標記

### 打斷 park 執行續
`pakr` 也是讓當前執行續停下來的一個方法。

```java
    private static void test() {
        Thread t1 = new Thread(() -> {
            log.info("park...");
            LockSupport.park();
            log.info("unpark...");
            log.info("Interrupter State: {}", Thread.currentThread().isInterrupted());
        }, "t1");

        t1.start();
    }
//     2021-07-08 16:46:57:609 INFO  [t1] c.ParkTest - park... 
```

執行 `LockSupport.park()` 時當前執行續就會暫停，但 main 執行續會繼執行。

這種可以使用 `interrupt()` 來將其打斷，繼續往下執行，如下。

```java
    private static void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("park...");
            LockSupport.park();
            log.info("unpark...");
            log.info("Interrupter State: {}", Thread.currentThread().isInterrupted());
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
//     2021-07-08 16:50:49:120 INFO  [t1] c.ParkTest - park...  
// 2021-07-08 16:50:50:121 INFO  [t1] c.ParkTest - unpark...  
// 2021-07-08 16:50:50:121 INFO  [main] c.ParkTest - End...  
// 2021-07-08 16:50:50:123 INFO  [t1] c.ParkTest - Interrupter State: true
```

>如果在調用一次 `LockSupport.park()` 不會產生任何效果，需要在執行之前調用 `Thread.interrupted()` 進行打斷標記重置


>`stop()`、`suspend()`、`resume()` 這些方法都以棄用，取而代之的是 `interrupt()`、`wait()`、`notify()`

### 主線程與守護線程

在預設情況下，JAVA 行程需要等待所有執行續結束，才會結束。但有一種特殊的執行續叫做守護執行續，只要是非守護執行續結束了，即使守護執行續沒執行完，也會強制結束。

JAVA 預設下是透過 main 執行續開始，只要其他執行續尚未結束該 JAVA 行程就不會結束。

下面範例透過設置*守護執行續*，也就是 `setDaemon()` 運行後，可以發現只要是 main 執行續完成任務後，`t1` 執行續也跟著被強制結束。
```java
@Slf4j(topic = "c.DaemonThreadTest")
public class DaemonThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            log.info("Thread t1 End...");
        }, "t1");    

        t1.setDaemon(true);
        t1.start();

        Thread.sleep(500);

        log.info("Main Thread End...");
    }
}
// 2021-07-09 11:15:03:295 INFO  [main] c.DaemonThreadTest - Main Thread End...  

```

>垃圾回收器執行續就是一種守護執行續


### 五種狀態

以作業系統層面

- 新建
    - 尚未與作業系統執行續無關連
- 就緒
    - 被 start() 後，等待 CPU 時間切片
- 運行
    - 獲得 CPU 資源，run() 定義執行續操作和功能
- 阻塞
    - 讓出 CPU 並臨時終止自己的執行，進入阻塞狀態
    - 執行 IO
- 死亡
    - 執行續完成了全部工作或被提前強制性中止或出現異常中止

![](https://i.imgur.com/DoZNz99.png)

在 JAVA 層面則是定義

- NEW
- RUNNABLE
    - 在 JAVA 中，運行狀態、阻塞狀態、就緒狀態都被視為此分類
- TERMINATED
- BLOCKED
- WAITING
    - `join()`
- TIMED_WAITING
    - 調用 `sleep()` 

## 共享模型之 Monitor

### 共享問題

[範例](src\main\java\com\example\cch\day04\ShareThreadTest.java)，輸出

```
2021-07-09 17:54:26:055 INFO  [t1] c.ShareThreadTest - T1 c = -2399  
2021-07-09 17:54:26:055 INFO  [t2] c.ShareThreadTest - T2 c = -5711  
2021-07-09 17:54:26:063 INFO  [main] c.ShareThreadTest - c = -5711
```

`static` 是一個讓執行續共享的變量，上述兩個執行續會交錯（CPU 時間片使用完了，執行 context switch）的存取 `static` 變量，而這會導致非預期的結果出現。


##### Critical Section
- 一個程序有多個執行續是沒問題的
- 上述範例問題會出在訪問*共享資源*上
    - 多個執行續共享資源是可行的
    - 但在共享資源上進行交互執行，問題就會出現
- 一個程式碼塊內如果存在對共享資源的多執行續讀寫操作，該段程式碼塊稱為 **Critical Section(臨界區)**


上面範例中

```java
for (int i=0; i<10000; i++){
    c++; // 臨界區
}
for (int i=0; i<10000; i++){
    c--; // 臨界區
}
```

##### Race Condition
多個執行續在臨界區內執行，由於程式碼塊的**執行序列不同**導致結果也可能是非預期，這稱之為*競爭條件(Race Condition)*

### Synchronized
##### 互斥
為了避免臨界區產生的競爭條件，有很多方式可以完成
- 阻塞式方案
    - `synchronized`
    - `Lock`
- 非阻塞式方案
    - 原子變量

`synchronized` 又稱對象鎖，採用*互斥方式*讓同一時刻至多只有一個執行續能持有對象鎖，當其它執行續想獲取該鎖就會鎖住。這能夠保證擁有該鎖的執行續可以安全執行臨界區的程式碼，不須擔心 `Context switch`。

>雖然 JAVA 中互斥和同步都可採用 `synchronized` 來完成，但依舊有區別
>互斥是保證臨界區的競爭條件發生，同一時刻只有一個執行續能夠執行臨界區程式碼
>同步是由於執行續執行先後順序不同，需要一個執行續等待其他執行續運行到某個點


[範例](src\main\java\com\example\cch\day04\ShareThreadTest.java)中透過加 `synchronized` 後，可以達到預期的效果

```java
public class ShareThreadTest {
    static int c = 0;
    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (obj) {
                    c++;
                }
            }
            log.info("T1 c = {}", c);
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (obj) {
                    c--;
                }
            }
            log.info("T2 c = {}", c);
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info("c = {}", c);
    }
}
// 2021-07-09 19:40:15:529 INFO  [t2] c.ShareThreadTest - T2 c = 0  
// 2021-07-09 19:40:15:529 INFO  [t1] c.ShareThreadTest - T1 c = 6277  
// 2021-07-09 19:40:15:539 INFO  [main] c.ShareThreadTest - c = 0
```

實際上 `synchronized` 保證臨界區程式碼的原子性。如果用以下方式呈現結果又會是如何呢 ?
- 把 `synchronized (obj)` 放在 `for` 迴圈外 ?
    - 對 `for` 迴圈整體做了原子性保護
- t1 有 `synchronized (obj)` 而 t2 有 `synchronized (obj2)` 運行又是 ?
    - 應該要針對同一個物件加鎖
- 如果 t1 有 `synchronized (obj)` 而 t2 沒有加會怎樣 ?
    - 無法有原子性保障，因為尚未加鎖的執行續會繼續往下執行

##### 方法上的 synchronized

```java
Class Test {
    public synchronized void t() {
    
    }
}

// 等價於

Class Test {
    public void t() {
        synchronized(this) {
        
        }
    }
}
```

##### 練習題

1. synchronized 鎖住哪一個物件

下面範例因為是同一個物件，因此有互斥效果
```java
@Slf4j(topic = "c.WhoSynchronizedObject")
public class WhoSynchronizedObject {
    public static void main(String[] args) {
        Number n = new Number();

        Thread t1 = new Thread(() -> {
            log.info("begin t1");
            n.a();
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.info("begin t2");
            n.b();
        }, "t2");

        t1.start();
        t2.start();

    }
}

@Slf4j(topic = "c.Number")
class Number {
    public synchronized void a() {
        log.info("a() method");
    }
    public synchronized void b() {
        log.info("b() method");
    }
}
// 2021-07-09 23:48:45:557 INFO  [t1] c.WhoSynchronizedObject - begin t1  
// 2021-07-09 23:48:45:563 INFO  [t1] c.Number - a() method  
// 2021-07-09 23:48:45:557 INFO  [t2] c.WhoSynchronizedObject - begin t2
// 2021-07-09 23:48:45:564 INFO  [t2] c.Number - b() method
```

下面範例同樣有互斥效果。

```java
@Slf4j(topic = "c.WhoSynchronizedObject")
public class WhoSynchronizedObject {
    public static void main(String[] args) {
        Number n = new Number();

        Thread t1 = new Thread(() -> {
            log.info("begin t1");
            try {
                n.a();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.info("begin t2");
            n.b();
        }, "t2");

        t1.start();
        t2.start();

    }
}

@Slf4j(topic = "c.Number")
class Number {
    public synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        log.info("a() method");
    }
    public synchronized void b() {
        log.info("b() method");
    }
}
// 2021-07-09 23:51:03:999 INFO  [t1] c.WhoSynchronizedObject - begin t1  
// 2021-07-09 23:51:03:999 INFO  [t2] c.WhoSynchronizedObject - begin t2  
// 2021-07-09 23:51:05:019 INFO  [t1] c.Number - a() method  
// 2021-07-09 23:51:05:020 INFO  [t2] c.Number - b() method 
```

下面範例新增一個未加鎖的方法 `c()`，因此結果有可能會有
- c -> 等待 1s -> a -> b
- b -> c -> 等待 1s -> a
- c -> b -> 等待 1s -> a

```java
@Slf4j(topic = "c.WhoSynchronizedObject")
public class WhoSynchronizedObject {
    public static void main(String[] args) {
        Number n = new Number();

        Thread t1 = new Thread(() -> {
            log.info("begin t1");
            try {
                n.a();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.info("begin t2");
            n.b();
        }, "t2");

        Thread t3 = new Thread(() -> {
            log.info("begin t3");
            n.c();
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

    }
}

@Slf4j(topic = "c.Number")
class Number {
    public synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        log.info("a() method");
    }
    public synchronized void b() {
        log.info("b() method");
    }

    public void c() {
        log.info("c() method");
    }
}

```

下面範例用了兩個物件分別執行執行續的動作，但是因為是不同物件，所以不會存在互斥。

結果會是 b -> a

```java
@Slf4j(topic = "c.WhoSynchronizedObject")
public class WhoSynchronizedObject {
    public static void main(String[] args) {
        Number n = new Number();
        Number n2 = new Number();
        Thread t1 = new Thread(() -> {
            log.info("begin t1");
            try {
                n.a();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.info("begin t2");
            n2.b();
        }, "t2");

        t1.start();
        t2.start();

    }
}

@Slf4j(topic = "c.Number")
class Number {
    public synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        log.info("a() method");
    }
    public synchronized void b() {
        log.info("b() method");
    }

}

```

下面範例是將 `a()` 設至為 `static`，因此這個鎖和 `b()` 不會產生互斥。如果 `b()` 設至為 static 就會有互斥效果。

```java
@Slf4j(topic = "c.WhoSynchronizedObject")
public class WhoSynchronizedObject {
    public static void main(String[] args) {
        Number n = new Number();
        Number n2 = new Number();
        Thread t1 = new Thread(() -> {
            log.info("begin t1");
            try {
                n.a();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.info("begin t2");
            n.b();
        }, "t2");
        t1.start();
        t2.start();

    }
}

@Slf4j(topic = "c.Number")
class Number {
    public static synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        log.info("a() method");
    }
    public synchronized void b() {
        log.info("b() method");
    }
}

```

下面範例同樣也是不同的實例，因此不會有互斥效果。但是如果 `b()` 為 static 則 n 和 n2 呼叫的方法會鎖同一個類對象，因此會有互斥。

```java
@Slf4j(topic = "c.WhoSynchronizedObject")
public class WhoSynchronizedObject {
    public static void main(String[] args) {
        Number n = new Number();
        Number n2 = new Number();
        Thread t1 = new Thread(() -> {
            log.info("begin t1");
            try {
                n.a();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.info("begin t2");
            n2.b();
        }, "t2");

        t1.start();
        t2.start();

    }
}

@Slf4j(topic = "c.Number")
class Number {
    public static synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        log.info("a() method");
    }
    public synchronized void b() {
        log.info("b() method");
    }

}
```

### 執行續安全分析
##### 類別中變量和靜態變量是否在執行續安全 ?
- 沒有共享，則執行續安全
- 如果被共享，根據其狀態是否能夠改變，又分兩種
    - 單純讀操作，執行續安全
    - 有讀寫操作，則該程式碼區塊是臨界區，會存在執行續安全問題
對於 `static` 方法來說，每個 `Thread` 都會有自己的 `stack`，因此是互不干擾。
##### 局部變量是否有執行續安全
- 局部變量是執行續安全
- 但局部變量引用的物件未必
    - 如果該對象沒有逃離方法的作用訪問，它是執行續安全
    - 如果該對象逃離方法的作用範圍，需要考慮執行續安全

以下面為例子，當多個執行續對一個物件中共用的 list 進行 `method()` 讀寫操作時會發生錯誤（index 為 -1）

```java
class ThreadUnsafe {
    List<Integer> list = new ArrayList<>();
    public void method(int v) {
        while (v > 0) {
            add();
            remove();
            v--;
        }
    }

    private void add() {
        list.add(1);    
    }

    private void remove() {
        list.remove(0);
    }
    
}
public class ObjectRefThreadTest {
    public static void main(String[] args) {
        ThreadUnsafe threadUnsafe = new ThreadUnsafe();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                threadUnsafe.method(600);
            }, "T"+(i+1)).start();
        }
    }
}
```

![](https://i.imgur.com/rv92LXF.png)


如果將 `list` 物件放進 `method()` 則它為執行續安全的，因為每當調用 `method()` 時該執行續會在 `heap` 中建立一個 `list` 物件，因此每個執行續不會相互干擾。


方法修飾符的思考，如果將 `add()`、`remove()` 改成 `public` 權限，代碼修正如下。不會有影響傳入 `List<Integer> list` 的參數並不會相互干擾。

```java
    public void method(int v) {
        List<Integer> list = new ArrayList<>();
        while (v > 0) {
            add(list);
            remove(list);
            v--;
        }
    }

    public void add(List<Integer> list) {
        list.add(1);    
    }

    public void remove(List<Integer> list) {
        list.remove(0);
    }
```

但是如下的繼層範例，存在者共享 `list` 資源的問題，因此 `final` 和 `private` 其實可以有效的控制執行續共享資源問題。

```java
class ThreadUnsafe {
    public void method(int v) {
        List<Integer> list = new ArrayList<>();
        while (v > 0) {
            add(list);
            remove(list);
            v--;
        }
    }

    public void add(List<Integer> list) {
        list.add(1);    
    }

    public void remove(List<Integer> list) {
        list.remove(0);
    }
}

class Sub extends ThreadUnsafe {
    @Override
    public void remove(List<Integer> list) {
        new Thread(() -> {
            list.remove(0);
        }).start();
    }
}
```

##### 常見執行續安全類
- String
- Integer
- StringBuffer
- Random
- Vector
- Hashtble
- java.util.concurrent 下的類

執行續安全可以說是，多個執行續調用它們同一個實例的某個方法，是執行續安全的。因此

- 每個方法都是原子
- 但多個方法的組合不是原子的
    - 以 `HashTable` 為例，可能 `get()`、`put()` 方法是有上鎖但是同時在一個代碼區塊中執行還是會存在不安全問題

> `synchronized` 加鎖動作在方法上，是針對該類。如果該方法上有涉及到另一個對象此時就會失效，因此需要找到共同的特徵進行 `synchronized。`

## JAVA 之 JMM

JAVA 規範了 JMM(Java Memory Model)，用來屏蔽調各種硬體和作業系統的記憶體訪問差異。這是為了不同平台上都能有一致的記憶體訪問效果。

JMM 本身是一種抽象概念並不真實存在它僅僅描述的是一組約定和規範，通過這組規範定義了程序中各個變量的讀寫訪問方式並決定一個執行續對共享變量的寫入何時以及如何變成對另一個線程可見，總體技術圍繞多線程的*原子性*、*可見性*和*有序性*。

### 可見性
當一個線程修改了某一個共享變量的值，其他線程是否能夠立即知道變更，JMM 規定了所有的變量都儲存在主記憶體中。

![](https://jenkov.com/images/java-concurrency/java-memory-model-6.png)

系統主要記憶體共享變量數據修改被寫入的時機是不確定的，多線程下很可能出現髒讀，所以每個線程都有自己的工作記憶體，線程自己的工作記憶體保存了該線程使用到的變量的主記憶體副本，線成對變量的所有操作都必須在自己的工作區進行，不能直接讀寫主記憶體變量。不同線程之間也無法相互讀取對方的工作區內容，因此值之間傳遞都透過主記憶體完成。


詳細內容[java-memory-model](https://jenkov.com/tutorials/java-concurrency/java-memory-model.html)
### 原子性
同一個操作是不可被打斷，在多線程環境下，操作不能被其他線程干擾。

### 有序性
對於一個線程執行程式碼而言，我們總是習慣認為程式碼執行是由上至下，有序執行。但為了提升效能，*編譯器和處理器通常會對指令序列進行重新排序*。JAVA 規範規定 JVM 線程內部維持順序化語意，即只要程序的最終結果與它順序執行結果相等，那麼指令的執行順序可以與程式碼順序不一致，這過程叫做指令的重排序。

```
原始程式碼 -> 編譯器優化排序 -> 指令級併行重排序 -> 記憶體系統重排序 -> 最終執行指令序列
```
指令重排可以保證串行語意一致，但沒有義務保證*多線程的語意也一致*(可能會有髒讀)，簡而言之，兩行以上不相干的程式碼再執行的時候有可能先執行的不是第一條，不見得是從上到下順序執行，執行順序會被優化。


## JMM 之 happens before 
它是判斷數據是否存在競爭，線程是否安全的非常有用手段。依賴這個原則，我們可以透過幾條簡單規則解決併發環境下兩個操作之間是否可能存在衝突的所有問題。

1. 如果第一個操作 happens-before 另一個操作，那麼第一個操作的執行結果將對第二個操作可見，而且第一個操作的執行順序排在第二個操作之前。
2. 兩個操作之間存在 happens-before 關係，並不意味著一定要按照 happens-before 原則制定的順序來執行。如果重新排序之後的結果與按照 happens-before 關係來執行結果一致，那個這種重新排序並不非法。

### 八條原則
1. 次序規則

一個線程內，按照程式碼順序寫在前面的操作先行發生於寫在後面的操作。就是前一個操作的結果可以被後續的操作獲取。
2. 鎖定規則

一個 `unlock` 操作先行發生於後面(時間上的先後)對同一個鎖的 `lock` 操作
3. volatile 變量規則

對一個 `volatile` 變量的讀寫操作先行發生於後面(時間上的先後)對這個變量的讀操作，前面的寫對後面的讀是可見的。
4. 傳遞規則

如果操作 A 先行發生於 B，而 B 又先行發生於操作 C，則可以得知操作 A 先行發生於操作 C
5. 線程啟動規則(Thread start rule)

Thread 物件的 `start()` 先行發生於此線程的每一個動作
6. 線程中斷規則(Thread interruption rule)

對線程 `interrupt()` 方法的調用先行發生於被中斷線程的程式碼檢測到中斷事件的發生。可以透過 `Thread.interrupted()` 檢測到是否發生中斷，也就是說要先調用 `interrupt()` 設置中斷的標誌，才能檢測到中斷發送
7. 線程終止規則(Thread termination rule)

線程中所有操作都先行發生於對此線程的終止檢測，可以透過 `isAlive()` 等手段檢測線程是否已經終止執行。
8. 對象終結規則(Finalizer Rule)

一個對象的初始化完成(建構函數執行結束)先行發生於它的 `finalize()` 方法的開始。物件沒有初始化完成之前，是不能調用 `finalize()` 方法



happens before 本質上是一種*可見性*。A Happens-before B 表示 A 發生過的事情對 B 來說是可見的，無論 A 事件和 B 事件是否發生在同一個線程裡。

## volatile 與 JMM
volatile 修飾兩大特點
1. 可見性 寫完後立即刷新回主記憶體並即時發送通知大家可回去主記憶體拿最新版本，前面的修改對後面所有線程可見
2. 有序性 禁止重新排序(存在數據依賴關係)

- 當寫一個 `volatile` 變量時，JMM 會把該線程對應的本地記憶體中的共享變量立*即刷新回主記憶體中*
- 當讀一個 `volatile` 變量時，JMM 會把該線程對應的本地記憶體設置為無效，*重新回到主記憶體中讀取最新共享變量*

對於 `volatile`  的寫是直接刷新到主記憶體中，讀是直接從主記憶體讀取。

`volatile` 是透過記憶體屏障來實現該兩大特點。

### 記憶體屏障(Memory Barrier)
記憶體屏障，是 CPU 或編譯器對記憶體隨機訪問的操作中的一個同步點，使得此點之前的所有讀寫操作都執行後才可以開始執行此點之後的操作。也控制特定條件下的重排序和記憶體可見性問題。其本身就是一種 JVM 指令，JMM 的重排規則會要求 JAVA 編譯器再生成 JVM 指令時插入特定的記憶體屏障指令，透過該記憶體屏障指令，對 `volatile` 實現了 JMM 中的可見性和有序性，但*無法保證原子性*。

記憶體屏障前的所有寫操作都要回到主記憶體中，記憶體屏障後的所有讀操作都能獲得記憶體屏障之前所有寫操作的最新結果(可見性)。

寫屏障(Store Memory Barrier)，告訴處理器在寫屏障之前將所有儲存在緩存中的數據同步到主記憶體中。也就是當看到 Store 屏障指令，就必須把指令之前所有寫入指令執行完畢才能繼續往下執行。

讀屏障(Load Memory Barrier)，處理器在讀屏障之後的讀操作，都在讀屏障之後執行。也就是說在 Load 屏障指令之後就能夠保證後面的讀取數據指令一定能夠讀取到最新的數據。

因次在重排序時，不允許把記憶體屏障之後的指令重新排序到憶體屏障之前。`volatile` 變量的寫，先行發生於任意後續對這個變量的讀，也較先寫後讀。

[infoq- memory_barriers_jvm_concurrency](https://www.infoq.com/articles/memory_barriers_jvm_concurrency/)
[gitbooks](https://luoyoubao.gitbooks.io/jvm/content/javanei-cun-mo-xing/nei-cun-ping-zhang.html)

屏障可再細分以下

|類型|指令|描述|
|---|---|---|
|LoadLoad|Load1;LoadLoad;Load2|保證 Load1 的讀取操作在 Load2 以及後續讀取操作之前執行|
|StoreStore|Store1;StoreStore;Store2|在 Store2 及其後的寫操作執行前，保證 Store1 的寫操作已刷新到主記憶體|
|LoadStore|Load1;LoadStore;Store2|在 Store2 及其後的寫操作執行前，保證 Load1 的讀操作已讀取結束|
|StoreLoad|Store1;StoreLoad;Load2|保證 Store1 的寫操作已刷新到主記憶體之後，Load2 及其後的讀操作才能執行|


**happens-before 之 volatile 變量規則**
|第一個操作|第二個操作: 普通讀寫|第二個操作: volatile 讀|第二個操作: volatile 寫|
|---|---|---|---|
|普通讀寫|可以重排|可以重排|不可以重排|
|volatile 讀|不可以重排|不可以重排|不可以重排|
|volatile 寫|可以重排|不可以重排|不可以重排|

當第一個操作為 volatile 讀時，不論第二個操作是什麼，都不能重排序。這操作保證了 volatile 讀之後的操作不會被重排到 volatile 讀之前。
當第二個操作為 volatile 寫時，不論第一個操作是什麼，都不能重排序。這操作保證了 volatile 寫之前的操作不會被重排到 volatile 寫之後。
當第一個操作為 volatile 寫時，第二個操作是volatile 讀時，不能重排序。

**保證可見性**

保證不同線程對某個變量完成操作後結果以及可見，及該共享變量一但改變所有線程立即可見。

```java
// app\src\main\java\com\cch\juc\day04\VolatileDemo.java
    static boolean flag = true;
    static volatile boolean flagVolatile = true;


    public static void noSee() throws InterruptedException {
        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            while(flag) {
            }
            log.info(String.format("Thread Name: %s, Flag : %s. Exist.", Thread.currentThread().getName(), flag));
        }, "t1").start();

        Thread.sleep(2000);

        flag = false;

        log.info(String.format("Thread Name: %s, Flag : %s", Thread.currentThread().getName(), flag));
    }

    public static void see() throws InterruptedException {
        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            while(flagVolatile) {
            }
            log.info(String.format("Thread Name: %s, Flag : %s. Exist.", Thread.currentThread().getName(), flagVolatile));
        }, "t1").start();

        Thread.sleep(2000);

        flagVolatile = false;

        log.info(String.format("Thread Name: %s, Flag : %s", Thread.currentThread().getName(), flagVolatile));
    }
```

`noSee` 方法會導致狀態被卡在 `while` 無限循環。其可能
1. main 線程修改了 flag 之後沒刷新到主記憶體，導致 `t1` 線程無法知道被更新。
2. flag 被 main 線程刷新至主記憶體，但 `t1` 讀到的都是自己工作記憶體內的 flag 值，沒有去主記憶體中更新獲取 flag 最新值

期望效果
1. 線程修改了自己工作記憶體中的副本之後，立即將其刷新至主記憶體
2. 工作記憶體中每次讀取共享變量時，都去主記憶體中從新獲取，然後拷貝至工作記憶體內

解決
使用 `volatile` 修飾共享變量，就可以達到期望的效果，被 `volatile` 修飾後具有以下特點
1. 線程中讀取的時候，每次讀取都會去主記憶體中讀取共享變量最新的值，然後將其複製到工作記憶體中
2. 線程中修改了工作記憶體中變量的副本，修改之後立即刷新到主記憶體

volatile變量的讀寫過程: 

`read(讀取) -> load(加載) -> use(使用) -> assign(賦值) -> store(儲存) -> write(寫入) -> lock(鎖定) -> unlock(解鎖)`


![](https://img-blog.csdnimg.cn/52321082da224755b5c744800eb81824.png)

JVM 記憶體指令與 `volatile` 相關的操作：
- read（讀取）：*作用於主記憶體變量*，把一個變量值從主記憶體傳輸到線程的工作記憶體中，以便隨後的 `load` 動作使用
- load（載入）：作用於工作記憶體的變量，它把 `read` 操作從主記憶體中得到的變量值放入*工作記憶體的變量副本中*
- use（使用）：作用於工作記憶體的變量，把工作記憶體中的一個變量值傳遞給執行引擎，每當虛擬機遇到一個需要使用變量的值的字節碼指令時將會執行這個操作
- assign（賦值）：作用於工作記憶體的變量，它把一個從執行引擎接收到的值賦值給工作記憶體的變量，每當虛擬機遇到一個給變量賦值的字節碼指令時執行這個操作
- store（儲存）：作用於工作記憶體的變量，把工作記憶體中的一個變量的值傳送到主記憶體中，以便隨後的 `write` 的操作
- write（寫入）：*作用於主記憶體的變量*，它把 `store` 操作從工作記憶體中一個變量的值傳送到主記憶體的變量中

上面六個元素只能保證單條指令的原子性，針對多條指令組合性的原子保證，沒有大面積加鎖，所以 JVM 提供另外兩個原子指令

- lock：*作用於主記憶體*，將一個變量標記為一個線程獨佔的狀態，只是寫的時候加鎖，就只是鎖了寫變量的過程
- unlock： *作用於主記憶體*，把一個處於鎖定狀態的變量釋放，然後才能被其他線程占用
Compare and swap(CAS)，實現併發算法時常用到的一種技術。其包含三個操作
- 記憶體位置
- 預期原值
- 更新值

執行 CAS 操作的時候，將記憶體位置的值與預期原值比較
如果相匹配，那麼處理器會自動將該位置值更新為新值
如過不匹配，處理器不做任何操作，多個線程同時執行 CAS 操作只有一個會成功


[compare-and-swap-cas-algorithm](https://howtodoinjava.com/java/multi-threading/compare-and-swap-cas-algorithm/)
[blog-cas-java](https://linianhui.github.io/java/cas/)

假設，記憶體位置為 V；預期原值為 A；更新值為 B
僅當舊的預期值 A 和記憶體中值 V 相同時，將記憶體中值 V 更新為 B，否則什麼都不做或重來，*當它重來重式的這種行為稱為自旋*。

```java
// app/src/main/java/com/cch/juc/day05/CompareAndSwapDemo.java
    static void test() {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean compareAndSet = atomicInteger.compareAndSet(5, 2023);
        log.info(String.format("isMatch: %s, value: %d", compareAndSet, atomicInteger.get()));
        boolean compareAndSet2 = atomicInteger.compareAndSet(5, 2023); // 值更新 2023，5 再去比較會失敗
        log.info(String.format("isMatch: %s, value: %d", compareAndSet2, atomicInteger.get()));
    }
// Apr 13, 2023 8:27:33 PM com.cch.juc.day05.CompareAndSwapDemo test
// INFO: isMatch: true, value: 2023
// Apr 13, 2023 8:27:33 PM com.cch.juc.day05.CompareAndSwapDemo test
// INFO: isMatch: false, value: 2023
```

CAS 屬於硬體級別保證，是一個 CPU 的原子指令(cmpxchg)，部會造成數據不一致問題。`cmpxchg` 執行時，會判斷是否為多核系統，是就加鎖。只有一個線程會對總線加鎖成功，加鎖成功之後再執行 CAS，相比 `synchronized` 重量級鎖性能會較好一些。對於自旋嘗試獲取鎖的線程不會立即阻塞，而是採用*循環*方式去嘗試獲取鎖，當線程發現鎖被占用時，會不斷循環判斷鎖的狀態，直到獲取，好處就是減少線程上下文切換的消耗，缺點是循環會消耗 CPU。

>Unsave 是 CAS 的核心類

CAS 將記憶體位置的值與預期原值比較，是就更新，這個過程是原子的。

在 JAVA 中，提供 `AtomicReference` 類別來自定義想要型別的原子操作。

[concurrency_atomicreference example (tutorialspoint)](https://www.tutorialspoint.com/java_concurrency/concurrency_atomicreference.htm)

[atomicreference example (geeksforgeeks)](https://www.geeksforgeeks.org/atomicreference-getandaccumulate-method-in-java-with-examples/)

透過 `AtomicReference` 實現自旋

```java
// app/src/main/java/com/cch/juc/day05/SpinLockDemo.java
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        // atomicReference.compareAndSet(null, thread) 沒被占用就進去
        log.info(String.format("Thread Name: %s, come in.", Thread.currentThread().getName()));
        while (!atomicReference.compareAndSet(null, thread)) {
            
        }
        
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        log.info(String.format("Thread Name: %s, take over.", Thread.currentThread().getName()));
    }
```

那 CAS 缺點是
- 循環開銷大
- ABA 問題

**ABA 問題**

CAS 算法實現一個重要前提須要取出記憶體中某時刻的數據並在當下時刻比較並替換，那麼在這個時間差類會導致數據變化。

假設一個線程 1 從記憶體位置 V 中取出 A，這時另一個線程 2 也從記憶體中取出 A，並且線程 2 進行了一些操作將直變成 B，然後線程 2 又將 V 位置的數據變成 A，這個時候線程 1 進行 CAS 操作發現記憶體中扔然是 A，預期 OK，然後線程 1 操作成功。

盡管線程 1 的 CAS 操作成功，但是不代表這個過程就是沒有問題的

[wiki](https://zh.wikipedia.org/zh-tw/%E6%AF%94%E8%BE%83%E5%B9%B6%E4%BA%A4%E6%8D%A2)
[aba-concurrency(baeldung)](https://www.baeldung.com/cs/aba-concurrency)
[aba-problem](https://www.educative.io/answers/what-is-the-aba-problem)

在 JAVA 中可以使用 `AtomicStampedReference` 物件來杜絕此問題，該類別多了一個版本號的紀錄。

```java
// app/src/main/java/com/cch/juc/day05/ABADemo.java
```


## 基本類型原子類
- AtomicInteger
- AtomicBoolean
- AtomicLong

# AbstractQueuedSynchronizer

主要用於解決鎖分配給誰的問題。整體就是一個抽象的 FIFO 隊列來完成資源獲取線程的排隊工作，並透過 int 型別變量表示持有鎖的狀態。

和 AQS 有關的物件
- RenntrantLock
- CountDownLatch
- ReentrantReadWriteLock
- Semaphore
- ...
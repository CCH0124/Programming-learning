# 枚舉與註解和集合框架
## 枚舉
- 定義枚舉類默認繼承 `jaava.lang.Enum` 類
- 常用方法
    - `values`
    - `valueOf`
    - `toString`
- 使用 enum 定義的枚舉類實現接口
    - 讓枚舉類的對象分別實現接口中的抽象方法
## 註解 Annotation
- 特殊標記，可在編譯、類加載、運行時被讀取，並執行相應的處裡


## 集合框架與陣列的比較
- 集合、陣列都是對多個數據進行儲存（記憶體層面）的操作，稱 JAVA 容器
- 陣列特點
    - 一旦初始化長度就確定了
    - 元素類型定義確定了，也只能操作指定類型的數據
    - 有序、可重複
- 陣列缺點
    - 長度不可變
    - 對添加、刪除、插入數據等，操作靈活性不高
    - 無序、不可重複，無法滿足
- JAVA 集合可分 Collection 和 Map 體系
    - Collection 單列數據，定義儲存一組對象方法的集合
        - `List` 有序、可重複
        - `Set` 無序、不可重複
    - Map 雙列數據，保存 key-value 映射關係的集合

### Collection 方法

![](https://static.javatpoint.com/images/java-collection-hierarchy.png)

- contains
    - 判斷時會調用 `obj` 物件所在類的 `equals`
- retainAll
    - 獲取交集
- 集合轉陣列
    - `toArray`
- 陣列轉集合
    - `Arrays.asList`
- Iterator 介面
    - 集合元素的遍歷操作
    - 集合物件每調用 `iterator` 方法都得到一個全新的迭代器物件，默認會在集合的第一個元素之前

:::info
儲存元素要求，添加的物件，所在類要重寫 `equals` 方法
:::
### List interface
- ArrayList
    - 底層用 Obkect[] 儲存數據
    - 執行續不安全
    - JDK 7 預設用長度 10 的陣列，在 JDK 8 中，指初始化並未給長度，第一次調用 `add` 時，才創建長度 10 的陣列
        - JDK 7 像是單例的餓漢式，JDK 8 則像單利的懶漢式，延遲陣列創建，節省記憶體
- LinkedList
    - 低層使用雙向鏈結儲存
    - 對於頻繁插入和刪除數據，用此類效率高於 `ArrayList`
- Vector
    - 底層用 `Obkect[]` 儲存數據
    - 因為是執行續安全，因此效率比 `ArrayList` 低
    - JDK 7、8 中建構方法建立物件時，低層都創建長度 10 的陣列。擴增，默認為原來陣列 2 倍

三個異同
都實現 `List` interface，儲存是有序的、可重複數據

`List` 中 `remove(int index)` 和 `remove(Object obj)` 是不同的，前者是索引後者用物件方式

### Set interface
- Set
    - 無定義額外方法，都已 `Collection` 中聲明過的方法
    - 無序性
        - 非隨機性
        - 順序根據 Hash 值決定
    - 不可重複性
        - 用 `equals()` 判斷時，不能返回 true
- HashSet
    - 執行續不安全
    - 可儲存 null
    - 底層用 HashMap 實現
- LinkedHashSet
    - 為 `HashSet` 子類，遍歷數據時，可按照添加順序遍歷
    - 對於頻繁操作，該效率高於 `HashSet`
- TreeSet
    - 可按照添加物件的指定屬性，進行排序
    - 底層使用紅黑數
    - 需要相同類的物件
    - 兩種排序方式，自然（實現 `Comparable`）和制定(`Comparator`)
    - 自然排序，`compareTo()` 不再是 `equals()`
    - 定制排序中，`compare()` 不再是 `equals()`

`Set` 添加元素過程，`HashSet`：
以 `Hash` 方式儲存值，`Hash` 相同則調用 `equals()`，並以鍊表方式儲存，JDK 7 指向原來元素，JDK 8 原來元素指向新元素。

**向 `Set` 添加數據，其所在的類一定要重寫 `hashCode` 或 `equals`**。

實現 `Set` 的類，底層都是 `HashMap`

### Map interface

- Map 的 key 是無序、不可重複的，使用 Set 儲存所有 Key
- Map 的 Value 是無序、可重複，使用 Collection 儲存所有 Value
- 一個 Key:Value 構成一個 Entry 物件，在 Map 中是無序的、不可重複，使用 Set 儲存所有 entry
- Key 所在的類要重寫 equals 和 hashCode，Value 只需重寫 equals，因為會封裝至 Entry，並且只須找 Key 即可得知 Value

- HashMap
    - Map 主要實現類
    - 執行續不安全，效率高
    - 儲存 null key 和 value
    - JDK 7 或之前底層使用陣列與鏈表，JDK 8 使用陣列與鏈表和紅黑樹
- LinkedHashMap
    - HashMap 子類
    - 可按照添加順序遍歷
- TreeMap
    - 用 key 排序，用自然排序或自訂排序
    - 底層使用紅黑數
- Hashtable
    - 較早之前實現類
    - 執行續安全，效率低
    - 無法儲存 null key 和 value
- Properties
    - Hashtable 子類
    - 用來處理配置文件
    - key、value 都是 String 類型

##### HashMap 實現原理 JDK7
```java=
HashMap map = new HashMap();
```
實例化 HashMap 之後，底層創建長度是 16 的一維陣列 Entry[] table。
```java=
map.put(key1,value1);
map.put(key2,value2);
map.put(key3,value3);
...
```
調用 key1 所在類的 hashCode() 計算該 Hash 值，該 hash 經過某種算法後，的到 Entry 陣列中的存放位置。該位置為空，則添加成功，否則需要比較 key1 和已經存在的數據 hash，如果 hash 都不同，則添加成功；如果和某一個數據 Hash 相同，繼續比較，調用 key1 所在類的 equals 進行比較，回傳 false，添加成功，否則 value1 進行覆蓋。

默認擴展是原本長度的 2 倍

##### HashMap 實現原理 JDK8
```java=
HashMap map = new HashMap();
```
底層無初始化陣列，底層是 Node[] 陣列，首次調用 `put` 時，底層才創建 16 長度的陣列，JDK7 是*陣列+鏈表*，JDK8 是*陣列+鏈表+紅黑樹*。
當陣列的某一個索引位置的元素以鏈表形式存在的數據個數大於 8 且當前的長度大於 64 時，此時該索引位置上所有數據改為使用紅黑樹儲存。

HashMap 預設長度 16，默認加載因子 0.75，擴容臨界值 `16*0.75`，Bucket 中鏈表長度大於默認值 8 轉化紅黑樹，Bucket 中的 Node 被樹化時最小的 hash 表容量是 64

##### 遍歷
`Map` 無 `iterator` 支援，可用 `keySet` 取得 Key 的 `Set` 再用 `iterator` 遍歷；可用 `values` 取的 value 的 `Collection` 再用 `iterator` 遍歷。

遍歷所有 key-value 使用 `entrySet` 用 `iterator` 遍歷，一個 entry 是一組 key-value，此時需要調用 `getKey` 和 `getValue`

### Collections
是操作 Set、List、Map 等集合的工具類。

Collection 和 Collections 區別 ? 前者是創建集合，後者是工具類可操作 Collection

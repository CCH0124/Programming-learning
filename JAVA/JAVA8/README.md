## Lambda 表達式
- 是一種匿名函數，可以理解為是一段可傳遞的程式碼

1. JAVA8 引入 Lambda 符號 `->`，左半部為表達式參數列表，右半部為表達式所需執行功能

- 無參數無返回值
- 有參數無返回值
- 若一個參數，小括號可省略
- 有兩個參數以上，有返回值，並有多個語句
- 只有一條語句，return 和大括號可以省略
- 類型推斷，JVM 編譯器透過上下文推斷
-  需要函數式接口的支持
    - 函數式接口是接口中只有一個抽象方法的接口
    - 可用 @FunctionInterface 修飾
        - 可以檢查是否是函數式接口

Lambda 範例如下
```java
(String s) -> s.length()
(Apple a) -> a.getWeight() > 150
(int x, int y) -> {
    System.out.println("Result:");
    System.out.println(x+y);
}
() -> 42
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()) 
```
##### 類型推斷
java7
```java
/**
 * Ok
 * */
String [] str = {"a", "b"}; 
/**
 * Error
 * */
String [] str; 
str = {"a", "b"}
```
在 java8 可依照參數推斷類型

```java
public void show(Map<String, Integer> map) {
    ...
}
show(new HashMap<>());
```

`FunctionInterface` 範例在 funint 目錄下。

### 内置核心函數式接口

函數接口就是只定義一個抽象方法的接口。

```java
public interface Adder{
    int add(int a, int b);
}
//  非函式接口，定義兩個 add 方法，一個從 Adder 來
public interface SmartAdder extends Adder{
    int add(double a, double b);
}
// 非函式接口，無定義方法
public interface Nothing{ 
} 
```

|函數式接口|參數類型|回傳類型|
|---|---|---|
|Consumer<T\>|T|void|
|Supplier<T\>|無|T|
|Function<T, R\>|T|R|
|Predicate<T\>|T|boolean|

範例在 `javaexistfunctionInterface` 目錄下

- [link](https://openhome.cc/Gossip/Java/ConsumerFunctionPredicateSupplier.html)
- [函數接口](http://tw.gitbook.net/java8/java8_functional_interfaces.html)

## 方法引用與構造器引用
若 `Lambda` 體中的內容有方法已經實現了，我們就可以使用**方法引用**。

其語法格式為以下：

- 三種語法格式
    - Object::實例方法名
    - Class::靜態方法
    - Class::實例方法名

>Lambda 體中調用方法的參數列表與返回值類型要與函數式接口中抽象方法的函數列表和返回值類型保持一致

>Lambda 參數列表中的第一參數是實例方法的調用者，而第二參數式實例方法參數時，可以使用 Class::method


構造器引用調用時，參數列表要和函數式接口中抽象方法參數列表保持一致。

## Stream API

![](https://i.imgur.com/JgECFt6.png)

相關範例在 streamapi 目錄下

在 JAVA 中 `Collection` 主要是儲存和訪問數據，而 `Stream` 主要用來描述對數據的運算。

### what is Stream
式數據渠道用於操作數據來源（Array、Collection等）所生成的元素序列。此流講的是數據。

:::danger
- Stream 自己不會儲存元素
- Stream 不會改變源對象。相反，它們會返回一個持有結果的新 Stream
- Stream 操作式延遲執行的。這意味著他們會等到需要結果的時後才執行。
:::

### Stream 的三步驟
- 建立 Stream
    - 一個源數據，取得一個流
- 中間操作
    - 一個中間操作鏈，對源數據進行處裡
- 終止操作
    - 執行中間的鏈，產生結果

![](https://i.imgur.com/dvD6b7A.png)

##### Stream 建立
- stream()
    - 順序流
- parallStream()
    - 並行流

##### Stream 中間操作
沒有終止操作觸發，中間操作不會執行任何處裡

- 篩選與切片
    - filter
        - 過濾某些元素
    - limit
        - 截斷
    - skip
        - 跳過元素
    - distinct
        - 篩選
        - 透過 `hashCode()` 和 `equals()` 去除重覆元素
    - etc.

- 映射
    - map
        - 將元素轉換成其他形式或提取訊息
        - 接收一個函數作為參數，該函數會被應用到每個元素上，並將其映射成一個新的元素
    - flatMap
        - 接收一個函數作為參數，將流中的每個值都轉換成另一個流，然後把所有流連接成一個流
    - map 與 flatMap 很像 add() 與 addAll() 的區別
- 排序
    - sorted()
        - 自然排序
    - sorted(Compareator com) 
        - 制定排序
##### Stream 終止操作
- 查找與匹配
    - allMatch(Predicate p)
        - 檢查是否頗配所有元素
    - anyMatch(Predicate p)
        - 檢查是否至少匹配一個元素
    - noneMatch(Predicate p)
        - 檢查是否沒有匹配所有元素
    - findFirst()
        - 返回第一個元素
    - findAny()
        - 返回當前流中的任意元素
    - count()
        - 返回流中元素總個數
    - max
        - 返回流中最大值
    - min
        - 返回流中最小值

##### reduce
- reduce(T identity, BinaryOperator b)/ reduce(BinaryOperator b)
    - 可將流中元素反覆結合起來，得到一個值
    - identity 起始值

>map 和 reduce 的連接通常稱為 map-reduce 模式，因 google 用他進行網路搜索而出名

##### collect
將流轉換為其他形式，接收一個 Collector 接口的實現，用於給 Stream 中元素做匯總的方法

## 並行流和數據流
並行流就是把一個內容分成多個數據，並用不同執行續分別處裡每個數據。Stream API 可以聲明性的通過 `parallel()` 與 `sequential()` 在並行流和順序流之間的進行切換。

相關操作在 parallel 目錄下
### fork/join 傳統執行續池的區別

採用 work-stealing 模式。將一個任務切分成更小的任務執行，並將小任務加到執行續對列中，然後再聰一個隨機執行續中的對列中偷一個並將它放在自己的對列中。在一般執行續中，可能會基於某些原因無法繼續執行，此時該執行續會處於等待裝態。

## Optional 
一個容器類，代表一個值存在或不存在。可以避免 `NullPointer` 異常。

- Optional.of(T t)
    - 創建一個 Optional 實例

```java
Optional<Employee> op = Optional.of(new Employee()); // of 參數不能為 null，較好發現 null 值在哪
Employee emp = op.get();
System.out.println(emp); // 輸出默認值
```
- Optional.empty()
    - 創建一個空的 Optional 實例
    - get 不到值
- Optional.ofNullable(T t)
    - 若 t 不為 null，創建 Optional 實例，否則創建空實例
    - get 不到值
- isPresent()
    - 判斷是否包含值
- orElse(T t)
    - 如果調用物件包含值，返回該值，否則返回 t
- orElseGet(Supplier s)
    - 如果調用物件包含值，返回該值，否則返回 s 獲取的值
- map(Function f)
    - 如果有值對其處裡，並返回處裡後的 Optional，否則返回 Optional.empty()
- flatMap(Function mapper)
    - 與 map 類似，要求返回值必須是 Optional

## interface 中的默認方法與靜態方法

```java
public interface Myfun {
    default String getName(){
        return "HA";
    }
}
```

```java
public class MyClass {
    public String getName(){
        return "HI";
    }
}
```
```java
public interface MyInterface {
    default String getName(){
        return "Ho";
    }
    public static void show(){
        System.out.println("static Method"); // 使用方式一樣，ClassName.staticMethod
    }
}
```

```java
public class SubClass extends MyClass implements Myfun {

}
```

```java
SubClass sc = new SubClass();
System.out.println(sc.getName()); // HI
```

interface 默認方法的類優先原則
可能在繼承和實作介面時，定義了相同的方法名稱時
- 選擇父類中的方法
    - 如果一個父類提供了具體的實現，那麼 interface 中具有相同名稱和參數的默認方法會被忽略
- interface 衝突
    - 如果一個父接口提供了一個默認方法，而另一個接口也提供相同的名稱和參數(不管是否為默認方法)，那麼必須覆蓋該方法來解決衝突 

```java
@Override
public String getName(){
    return MyInterface.super.getName(); // 明確覆寫哪個接口
}
```

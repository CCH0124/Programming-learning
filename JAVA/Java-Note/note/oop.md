## 繼承 
### OOP 繼承性
- 好處
    - 減少代碼冗余，增加覆用性
    - 便於功能的擴展
    - 多態性

- 子類繼承父類後，子類會獲取父類的結構、屬性、方法，如果為 private，因為封裝性影響，子類不能直接調用父類結構。
- 子類繼承之後可以聲名自己持有的屬性和方法，實現功能擴展
- 在 JAVA 只支持單繼承和多層繼承，無多重繼承
    - 一個類能被多個子類繼承
    - 一個類只能有一個父類
- 所有 java 類（除 java.lang.Object）直接會間接繼承 java.lang.Object 類

### override
- **回傳類型、方法名和參數個數需一致**
- 子類重寫的方法權限修飾不小於父類被重寫的方法的權限
- 子類拋出的異常範圍不能大於父類

### super 
- 用來調用
    - 屬性
    - 方法
    - 建構方法
- 在子類的方法或建構方法中，透過使用`super.屬性` 或 `super.方法` 的方式，顯示的調用父類中聲明的屬性或方法，通常都省略
- 當子類和父類中定義同名屬性時，要從子類調用父類的屬性，則必須使用 `super.屬性` 或 `super.方法`
- 在建構方法中使用 `super(參數列表)`
    - 需再首行
        - 無聲明 `this()` `super()` ，默認使用父類別中空參的建構方法
    - 與 `this` 不能同時使用
為何不能與 this 出現在同一個建構方法 ?(因為要寫在首行)
為何只能在第一句出現 ?(初始化)
## 多態性
- 父類的引用指向子類的對象
- 當調用父子類同名同參數方法時，實際執行的是子類重寫父類的方法 ---- 虛擬方法調用
    - 編譯時只能調用父類聲名的方法，但在運行期，實際是子類重寫父類的方法
- 多態的前提
    - 類的繼承關係
    - 方法的重寫
- 物件的多態性，適用於方法，不是用於屬性
- 屬於**運行時行為**
    - 因為不明確結果
- 對於`重載`而言，在方法調用之前，編譯器就已經確定了所要調用的方法，這稱為**靜態綁定**；對於**多態**而言，只有等到方法調用的那一刻，編譯器才會確定所要調用的具體方法，這稱為**動態綁定**
- interface、Abstract 肯定體現多態性

### 向下轉型
- 就是多態
- `instanceof`
    - 判斷物件 a 是否為類 A 的實例
- 為了避免向下轉型出現 `ClassCastException` 異常，在轉型之前，先使用 `instanceof` 進行判斷


##### Example
```java=
public static void main(String [] args){
    Base base = new Sub();
    base.add(1,2,3); // sub
    
    Sub s = (Sub)base;
    s.add(1,2,3); // sub2
}
class Base {
    public void add (int a, int... arr) {
        System.out.println("Base");
    }
}
class Sub extends Base {
    public void add (int a, int [] arr) {
        System.out.println("sub");
    }
    public void add (int a, int b, int c) {
        System.out.println("sub2");
    }
}
```


## Object Class
- Object Class 所有類的根父類

## == 和 equal()
- 可使用在基本數據類型和引用類型
- 如果比較是基本數據類型，比較兩個變量保存的數據是否相同(數據類型不一定要相等)；如果比較是引用數據類型，比較兩個物件的地址是否相同，即兩個引用是否指向同一個物件實體
- equal 適用於引用數據類型
    - Object 類別裡的 equals 等同於 `==`，File 或 Date 等類別都會重寫

## toString 
- Object Class 中定義
    - 預設回傳 ` getClass().getName() + '@' + Integer.toHexString(hashCode())`

## Wrapper Class
 
|基本數據類型|包裝類|
|---|---|
|byte|Byte|
|short|Short|
|int|Integer|
|long|Long|
|float|Float|
|double|Double|
|boolean|Boolean|
|char|Character|

- 前六個數值型包裝類父類為 `Number`
    - 無加減乘除
- 基本數據類型和包裝類是無關係的
- `equals` 無法用於基本數據類型
- 包裝類會有 null


Wrapper 轉基本類型
```java=
Integer in1 = new Integer(12);
int i1 = in1.intValue(); // 轉基本類型
```

編譯時類型會比對
```java=
Object o1 = true ? new Integer(1) : new Double(2.0);
System.out.println(o1);//1.0 

Object 02;
if(true) {
    o2 = new Integer(1);
} else {
    o2 = new Double(2.0);
}
System.out.println(o2);//1
```

`Integer` 內部定義了 `IntegerCache` 結構，IntegerCache 中定義了 `Integer[]`，保存了從 -128~127 範圍的整數。如果使用自動裝箱的方式，給 Integer 賦值的範圍在 -128~127 範圍內時，可以直接使用陣列中的元素，不用再去 `new` 了。

## static
編寫一個類時，只有透過 new 關鍵字才會產生出物件，此時系統才會分配記憶體空間給該物件，這樣才能供外部調用。但某些特定數據在記憶體空間只有一份。台灣是個國家名稱，每一個台灣人都共享此國家名稱，不必每一個人的實例都分配一個代表國家名稱的變量。

- static 修飾的變量是共享的
- 因為 class 只會加載一次，則 static 變量在記憶體中也只會存在一份，存在方法區的靜態域
- 靜態方法中，只能調用靜態的方法或屬性，非靜態方法，既可調用非靜態的方法或屬性，也可以調用靜態的方法或屬性
- 在靜態方法內，不能使用 this、super 關鍵字，生命週期
- 操作靜態屬性的方法，通常設置為 static 
- 工具類中的方法，習慣聲名為 static

## Class 成員之一代碼區塊
- 用來初始類和對象
- 有修飾的話只能用 static
- 比較
    - 靜態代碼塊
        - 內部可以有輸出句
        - 隨著類加載而執行，且只執行一次
        - 初始化類的訊息
        - 如果一個類中定義了多個靜態代碼塊，則按照宣告先後順序執行
        - 靜態代碼塊的執行要優先於非靜態代碼塊的執行
        - 靜態代碼塊內只調用靜態的屬性、靜態的方法，不能調用非靜態的結構
    - 非靜態代碼塊
        - 內部可以有輸出句
        - 隨著物件的創見而執行
        - 每創建一個物件，就執行一次非靜態代碼塊
        - 可以在創建對象時，隊對象的屬性等進行初始化
        - 如果一個類中定義了多個非靜態代碼塊，則按照宣告先後順序執行
        - 非靜態代碼塊內只調用靜態的屬性、靜態的方法，也能調用非靜態的結構


## 屬性賦值順序
1. 默認初始化
2. 顯示初始化、代碼區塊賦值（需要看前後）
3. 建構方法初始化
4. 有了物件後，透過"Object.屬性"或"Object.方法"進行賦值

## final 關鍵字
- 可用來修飾 Class、method、variable
- final class
    - 無法被繼承
    - 在 java 中有 String、System、StringBuffer 類等
- final method
    - 無法重寫該方法
    - Object 類中的 getClass
- final variable
    - 該變數就稱為**常量**
    - 賦值方式
        - 顯示初始化
        - 代碼區塊中初始化
            - 如果用方法賦值，可能需要處裡異常時
        - 建構方法中
            - 在建立該物件時，要的內容不一樣
    - 修飾局部變數
    - 方法中的參數
        - 表示參數是一個常量。調用後，給該常量賦值，只能在方法體內使用，無法再更改
- static final
    - 用來修飾

```java=
public int add(final int a) {
    return a++; // error
    return a + 1;
}
```
```java=
public class Something {
    public static void main(String [] argv) {
        Other o = new Other();
        new Something().addOne(o);
    }
    public void addOne(final Other o){
        // o = new Other()
        o.i++; // o 沒有重新賦直
    }
}
class Other {
    public int i;
}
```
## 抽象類別和抽象方法
- abstract
    - 抽象 class
        - 不能實例化
        - 提供建構方法
    - 抽象 Method
        - 只有方法的宣告，無方法內容
        - 有抽象方法，一定是抽象類
        - 子類繼承抽象類別，需複寫抽象方法
        - 若子類沒有重寫父類中的抽象方法，此子類也是一個抽象類
    - 無法修飾屬性、建構方法、私有方法、靜態方法、final 方法、final 類別
### 創建抽像類的匿名子類對象
```java=
public class PersonTest{
    public static void main(String[] args){
        method(new Student()); // 匿名物件
        Worker worker = new Worker();
        method1(worker);// 非匿名的類非匿名的物件
        method1(new worker()); // 非匿名的類匿名的物件
        
        Person p = new Person(){ // 創建匿名子類的對象
            /**
             * 當成子類，只有子類才能覆寫
             * */
            @Override
            public void eat(){
            
            }
            @Override
            public void breath(){
            
            }
        };
    }
    public static void method1(Person p){
        p.eat();
        p.walk();
    }
    public static void method(Student s){
        
    }
}
class Worker extends Prson{
    ...
    ...
}
```

## interface
- 有多重繼承效果
- 沒有 is-a 關係，是 has a
    - 定義共同的行為特徵
- extends 是一個是不是的關係，interface 是能不能的關係
- interface 本質是契約、標準、規範
- 不能定義建構方法，表示無法實例化
- 如果實現類沒有覆蓋 interface 中所有抽象方法。則此實現仍然為一個抽象類
- interface 與 interface 之間可以繼承，也可以多繼承
- interface 多態性
```java=
interface AA {

}
interface BB {

}
interface CC extends AA, BB {

}
```
定義的結構
- JDK7
    - 只能定義
        - 全域常量
            - public static final 
        - 抽象方法
            - public abstrace
- JDK8
    - 全域常量
    - 抽象方法
    - 靜態方法
    - 默認方法
    - 等

### abstract 和 interface 差異
- 相同
    - 不能實例
    - 都可被繼承
- 不同
    - 抽象有建構方法
    - interface 不能宣告建構方法
    - 類，單繼承性
    - interface，多繼承性，類 interface 多實現
### interface 應用
- 代理模式（Proxy）
- 工廠模式

### 除錯
```java=
interface A {
    int x = 0;
}
class B {
    int x = 1;
}
class C extends B implements A {
    public void px(){
        System.out.println(x); // 不知道是哪一個
    }
    public static void main(String[] args){
        new C().pX();
    }
}
```

### JAVA8 interface
- 靜態方法
    - `static` 修飾
    - 只能藉由 interface 自己調用
- 默認方法
    - `default` 修飾
    - 透過實現類對象調用
    - 可以 `override`
    - 如果子類(或實現類)繼承的父類和實現的 interface 聲明同名同參數的方法，那麼子類在沒有重寫此方法下，默認調用的是父類中的
        - 類優先原則
    - 如果實現類實現多個 interface，而這多個 interface 中定義了同名同參的默認方法，那麼實現類沒有重寫此方法的情況下
        - 接口衝突

```java=
public interface CompareA{
    public static void method1(){
        System.out.println("...");
    }
    public default void method2(){
        System.out.println("...");
    }
    default void method3(){
        System.out.println("...");
    }
}
```
```java=
public void myMethod() {
    method3();
    super.method3();
    // 調用 interdace 中默認方法
    CompareA.super.method3();
    CompareB.super.method3()
}
```

## 內部類 
類別中的類別
- Inner class 一般用在定義他的類或語句區塊之內，在外部引用時必須給出完整的名稱
    - Inner class 的名字不能包含它的外部類類名相同
- 分類
    - 成員內部類
        - static 成員內部類和非 static 成員內部類
    - 局部內部類
        - 匿名內部類
        - 方法內
        - 代碼區塊內
        - 建構方法內
- 成員內部類
    - 作為外部類成員
        - 調用外部類結構
        - static 修飾
        - 可被權限修飾
    - 作為一個類
        - 可聲明屬性、方法、建構方法
        - 可被 final 修飾
        - 繼承
        - 可被 abstract 
- 如何實例化成員內部類
- 如何在成員內部類中區分調用外部類的結構
- 開發中局部內部類的使用
- 編譯後，外部和內部會生成 bytecode 檔案
    - 成員內部類
        - 外部類$內部類名.class
    - 局部內部類
        - 外部類$數字 內部類.class
```java=
public static void main(String [] args) {
    Person.Dog dog = new Person.Dog(); // 靜態 Dog 實例
    dog.method();
    Person p = new Person();
    Person.Cat cat = p.new Cat(); // 非靜態實例
    cat.method();
}
class Person {
    String name;
    // 成員內部類
    static class Dog {
        
    }
    class Cat{
        String name;
        Person.this.method(); // 調用外部類方法
        public void display(String name) {
            System.out.println(name); // 參數
            System.out.println(this.name); // 內部類
            System.out.println(Person.this.name);// 外部類
        }
    }
    public Person(){
        class CC {
        
        }
    }
    public void method() {
        class AA {
            
        }
    }
    {
        class BB {
        
        }
    }
}
```
```java=
public class InnerClass {
    public void method(){
        class AA{
            
        }
    }
    public Comparable getComparable(){
        // 局部內部類
        class MyComparable implement Comparable{
            @Override
            public int compareTo(Object o){
                return 0;
            }
        }
        return new MyComparable();
    }
}
```

### 內部類使用注意點
- 在局部內部類的方法中，如果調用局部內部類所聲明的方法中的局部變量，要求該局部變量為 final，jdk 1.7 前都是這樣要求，JAVA 8 之後可省略，預設就是



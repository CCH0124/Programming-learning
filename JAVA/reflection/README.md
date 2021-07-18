# Reflection

反射（reflection）是被視為動態語言的關鍵，允許程序在執行期間借助於 Reflection API 取得任何類的內部資訊，並能直接操作任意對象的內部屬性和方法。在設計模式和框架底層都會使用到。

加載完類之後，在 heap 記憶體的方法區就產生一個 Class 類型的物件，此物件有該類完整資訊，可藉由該物件看到 Class 結構。

Java 程序上基本有三個階段

- 代碼階段/編譯階段
    - 將一個 Class 透過 javac 產生 bytecode 也就是 .class 檔案
- Class 階段（加載階段）
    - 透過 ClassLoader 將 .class 加載 Class 物件至 Heap 上
    - 將變量轉換 `Field[] fields`；將建構方法轉成 `Constructor[] cons`；將方法轉換成 `Methods[] ms`
- Runtime 運行階段
    - 反射應用，獲取 class 物件，創建物件獲取方法操作屬性等

一般：引入 package -> new 實例化 -> 取得物件 
反射：物件 -> getClass() -> 得到完整 package


### 反射能得到的功能
- 在運行時判斷任意一個物件所屬類
- 在運行時構造任意一個類的物件
- 在運行時判斷任意一個類所具有的屬性和方法
- 在運行時獲取泛型訊息
- 在運行時調用任一個物件的屬性和方法
- 在運行時處裡註解
- 生成動態代理

### 反射主要類
- java.lang.Class
    - Class 物件表示某個類加載後在 Heap 中的物件
- java.lang.reflect.Method
    - 某一個類的方法
- java.lang.reflect.Field
    - 某一個類的變數
- java.lang.reflect.Constructor
    - 類的建構方法

>反射是動態性，可針對不確定請求來做應用
>反射缺點是對執行速度有影響

使用範例

```java=
public class ReflectionMethodTest {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("com.example.cch.day01.Cat");
        Object obj = cls.getDeclaredConstructor().newInstance();

        // 無法獲取 private 的屬性
        // Field name = cls.getField("name");
        // System.out.println(name.get(obj));

        Field age = cls.getField("age");
        System.out.println(age.get(obj));
        
        // 沒有參數表示該構造是無帶入參數
        Constructor constructor = cls.getConstructor();
        System.out.println(constructor);
        
        // 該構造器有一個 String 類型的參數
        
        Constructor constructor1 = cls.getConstructor(String.class);
        System.out.println(constructor1);
    }
}
// 2
// public com.example.cch.day01.Cat()
// public com.example.cch.day01.Cat(java.lang.String)
```

性能比較[程式碼](PerformenceTest.java)，雖然反射影響性能，但還是可以優化。透過 `setAccessible` 方式，其功用是是否要安全檢查，當使用 false 會有優化效果。不過在實驗過程中不一定會有效果。

## Class 類
- Class 是一種類，同樣繼層 Object 類
- 他不使用 `new` 關鍵字產生物件，而是透過系統創建

傳統建立物件：
```java=
Cat c = new Cat(); // 產生物件時會透過 loadClass 進行加載
```
透過 debug 模式，可以看見他會進入到 `loadClass` 方法
```java=
public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }
```

反射模式方式：

```java=
Class catClass = Class.forName("com.example.cch.day01.Cat");
```

debug 模式追蹤，
```java=
    public static Class<?> forName(String className)
                throws ClassNotFoundException {
        Class<?> caller = Reflection.getCallerClass();
        return forName0(className, true, ClassLoader.getClassLoader(caller), caller);
    }
```

```java=
static ClassLoader getClassLoader(Class<?> caller) {
        // This can be null if the VM is requesting it
        if (caller == null) {
            return null;
        }
        // Circumvent security check since this is package-private
        return caller.getClassLoader0();
    }
```


```java=
ClassLoader getClassLoader0() { return classLoader; }
```
最後傳進去的值和傳統建立物件方式一樣，透過 ClassLoader 類加載 Class 物件

```java=
public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }
```

- 對每個類的 Class 類對象在記憶體中只存在一份，因為類加載過程只有一次，在實驗時須注意

```java=
        Class catClass = Class.forName("com.example.cch.day01.Cat");

        Class catClass2 = Class.forName("com.example.cch.day01.Cat");
        System.out.println(catClass.hashCode() == catClass2.hashCode());
```

- 藉由 Class 類可以得到一個 Class 完整結構
    - getSuperClass() 獲取父類
    - getInterfaces() 獲取 interface 
    - 等
- Class 物件是存放在 Heap 中
- ByteCode 會存放至方法區，而方法區會引用 Class 對象


### 使用範例

```java=
        String carClassPath = "com.example.cch.day03.Car";
        // Get Car class
        Class<?> cls = Class.forName(carClassPath);
        // 顯示 cls 對象是哪個類的 Class 對象
        System.out.println("cls 的 Class 對象: " + cls);
        // 輸出 cls 運行類型
        System.out.println("cls 運行類型: " + cls.getClass());
        System.out.println("Get Package name: " + cls.getPackageName());
        System.out.println("Get full class name: " + cls.getName());
        // 建立一個 instance
        Car c = (Car)cls.getDeclaredConstructor().newInstance();
        System.out.println(c);
        // 獲取屬性
        Field brand = cls.getField("brand");
        System.out.println(brand.get(c));

        // 屬性賦值
        brand.set(c, "瑪莎拉蒂");
        System.out.println(brand.get(c));

        // 獲取所有屬性

        Field [] fields = cls.getFields();
        Arrays.stream(fields).forEach(f -> f.getName());

```

### 獲取 Class 類對象

```java=
        // Type 1
        // 編譯階段
        // 配置文件
        Class<?> cls = Class.forName(carClassPath);

        // Type 2
        // 加載階段
        // 參數傳遞
        Class cls2 = Car.class;

        // Type 3
        // 運行階段
        // 有實例存在
        Car c = new Car();

        Class cls3 = c.getClass();

        // Type 4
        // 類加載器方式
        // 每個物件都有類加載器
        ClassLoader classLoader = c.getClass().getClassLoader();

        Class cls4 = classLoader.loadClass(carClassPath);
```

因為一個 Class 只會被加載一次，它們生成的 Class 對象 hashCode 都是一致。


###### class 加載過程

1. 經過 javac 命令以後，會生成一個或多個 bytecode 檔案，接者使用 javac 對某個 bytecode 進行解釋運行。相當於將某個 bytecode 檔案加載至記憶體。此過程為類加載。加載到記憶體的類，稱運行類，該運行類作為 Class 的一個實例
2. Class 的實例對應一個運行時的類
3. 加載到記憶體的運行類，會緩存一定的時間，該時間內可以來獲取運行時類

靜態加載，編譯時依賴性強；動態加載，編譯時彈性大，運行時才加載。

類加載時機是 
1. 創建物件時 
2. 當子類被加載時
3. 調用類中 static 成員
4. 透過反射

前三者屬於靜態加載，後者是動態加載
##### 哪些類型可存在 Class 對象
- class
    - 外部
    ```java=
    Class<String> cls = String.class
    ```
    - 局部內部
    
    - 匿名內部
- interface
```java=
Class<Serializable> cls = Serializable.class
```
- array
```java=
Class<Integer[]> cls = Integer[].class;
Class<Integer[][]> cls = Integer[][].class;
```
- enum
```java=
Class<Thread.State> cls = Thread.State.class;
```
- annotation
```java=
Class<Deprecated> cls = Deprecated.class
```
- primitive type
```java=
Class<Long> cls = Long.class;
```
- void
```java=
Class<Void> cls = void.class;
```


## class 的加載與 classLoader 理解
當成續使用某個類時，如果該類還未被加載到記憶體中，則系統會通過下方流程對該類進行出始化

1. Load 將 class 檔案讀入記憶體，並創建一個 java.lang.Class 物件
2. Link 把類的兩進制數據合併到 JRE 中
3. Initialize JVM 負責對類進行初始化

上述三階段是 Loading -> Linking -> initialization

### 類加載階段 Loading
class 檔案 bytecode 內容加載制記憶體，兵將這些靜態數據轉換成方法區的運行時數據結構，接著在 heap 中生成一個代表這個類的 java.lang.Class 物件，作為方法區中數據的存取入口

### 類加載階段 Linking
##### 驗證 verification
1. 目的是確保 Class 二進製檔案包含的訊息符合當前虛擬機需求，並且不危害虛擬機自身安全
2. 檔案格式驗證（是否以 oxcafebabe 開頭）、ByteCode 驗證等
3. -Xverify:none 參數可縮短類加載時間

##### Preparation
1. JVM 會在該階段時對靜態變量分配記憶體初始化（數據類型的初始值）。兒所使用的記憶體都在方法區中進行分配

##### Resolution
1. 虛擬機將常量池的符號引用替換為直接引用（記憶體）過程

### initialization
1. 到初始化階段，才真正開始執行類中定義的 Java 程式碼，此階段是執行 `<client>()` 方法過程
2. `<client>()` 由編譯器按語句在原始檔案中出現的順序，依次自動蒐集類中所有靜態變量的賦值動作和靜態程式碼塊中的語句並進行合併
3. 虛擬機會保證 `<client>()` 方法在有多執行續環境中被正確的加鎖、同步，因此多個執行續同時初始化一個類，會友阻塞機制。


## 透過反射獲取類的結構訊息
基本上其調用方法名能夠很清楚描述他獲取的資訊是什麼

1. `getModifiers()` 以 int 返回修飾符號
- 預設 0
- public 1
- private 2
- protected 4
- static 8
- final 16
如果一個變量是 `public static` 則會回傳 9(1+8)，方法也是


## 透過反射建立物件
1. 無參建構方法
2. 有參建構方法

在 Class 類相關方法
- `newInstance`
- `getConstructor(Class... clazz)` 根據參數列表獲取對應的 public 建構方法
- `getDecalaredConstructor(Class... clazz)` 根據參數列表獲取對應的建構方法

Constructor 類相關方法
- `setAccessible`
    - 強制對 private 進行存取
- `newInstance(Object... obj)`

```java=
public class NewInstanceTest {
    public static void main(String[] args) throws Exception {
        Class<?> userClass = Class.forName("com.example.cch.day05.User");
        // No Arg Constructor
        Object o = userClass.getDeclaredConstructor().newInstance();
        System.out.println(o);
        // call have arg public constructor
        Constructor<?> constructor = userClass.getConstructor(String.class);
        o = constructor.newInstance("HSP");
        System.out.println(o);
        // call private constructor
        Constructor<?> constructor1 = userClass.getDeclaredConstructor(int.class, String.class);
        constructor1.setAccessible(true); // 使用反射訪問私有權限
        o = constructor1.newInstance(37, "naruto");
        System.out.println(o);
        
    }
}
```
> 方法中有 Declared 字眼表示獲取所有(含 private)
## 反射存取類中成員

- `getDeclaredField()`
- `setAccessible`
    - 可存取 private
- `set(Object, value)`
    - 設值
- `get(Object)`
    - 取值

> 方法中有 Declared 字眼表示獲取所有(含 private)

```java=
public class AccessPropertyTest {
    public static void main(String[] args) throws Exception{
        Class<?> stuClass = Class.forName("com.example.cch.day06.Student");
        Object o = stuClass.getDeclaredConstructor().newInstance();

        Field age = stuClass.getField("age");
        age.set(o, 16); // 操作屬性
        System.out.println(o);
        System.out.println(age.get(o)); // 需有 getAge 方法

        Field name = stuClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(o, "Naruto"); // name 為 static 因此物件可傳入 null
        System.out.println(o);
    }    
}
```

## 透果映射訪問類中的方法
- `getDeclaredMethod(METHOD_NAME, {}.class)`
- `setAccessible`
    - 可對 private 訪問
- `invoke(Object, ARG_LIST)`
    - 操作 
    - static 的方法，Object 可為 null
    - 該方法有返回值一律都是 Object 類型
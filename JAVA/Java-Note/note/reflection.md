# 反射機制

反射（reflection）是被視為*動態語言*的關鍵，允許程序在執行期間借助於 Reflection API 取得任何類的內部資訊，並能直接操作任意對象的內部屬性和方法。

加載完類之後，在 heap 記憶體的方法區就產生一個 Class 類型的物件，此物件有該類完整資訊，可藉由該物件看到 Class 結構

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

### 反射與封裝 
反射是動態性，可針對不確定請求來做應用
## 獲取 class 實例 
- 獲取方式
    - .class
        - 編譯時報錯
    - .getClass
    - Class.forName
        - 運行時，才報錯
        - 用的多
    - ClassLoader
- class 加載過程
1. 經過 javac 命令以後，會生成一個或多個 bytecode 檔案，接者使用 javac 對某個 bytecode 進行解釋運行。相當於將某個 bytecode 檔案加載至記憶體。此過程為類加載。加載到記憶體的類，稱*運行類*，該運行類作為 Class 的一個實例
2. Class 的實例對應一個運行時的類
3. 加載到記憶體的運行類，會*緩存*一定的時間，該時間內可以來獲取運行時類

- 哪些類型可存在 Class 對象
    - class
        - 外部
        - 局部內部
        - 匿名內部
    - interface
    - array
    - enum
    - annotation
    - primitive type
    - void

## class 的加載與 classLoader 理解
當成續使用某個類時，如果該類還未被加載到記憶體中，則系統會通過下方流程對該類進行出始化
1. Load
將 class 檔案讀入記憶體，並創建一個 `java.lang.Class` 物件
2. Link
把類的兩進制數據合併到 JRE 中
3. Initialize
JVM 負責對類進行初始化

- 類加載作用
    - class 檔案 bytecode 內容加載制記憶體，兵將這些靜態數據轉換成方法區的運行時數據結構，接著在 heap 中生成一個代表這個類的 java.lang.Class 物件，作為方法區中數據的存取入口

### ClassLoader
自定義類加載器 -> System Classloader -> Extension Classloader -> Bootstap Classloader

-  Bootstap Classloader
    -  JVM 自帶的類加載器，負責 JAVA 平台核心庫
-  Extension Classloader
    -  負責 jar/lib/ext 目錄下的 jat package 或 -D java.ext.dir 指定目錄下的 jar package
-  System Classloader
    -  負責 java -classpath 或 -D java.class.path 所指的目錄下的類與 jar 包裝入工作
    -  最常用的加載器
```java=
         Properties pro = new Properties();
        // 讀取配置檔案方式一
        // 預設檔案在 module 
       FileInputStream fis = new FileInputStream("src/jdbc.properties");
       pro.load(fis);
        // 使用 ClassLoader
        // 預設檔案在 src
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is =             classLoader.getResourceAsStream("src/jdbc1.properties");
        pro.load(is);
        
        String user = pro.getProperty("user");
        String pwd = pro.getProperty("passwd");
        System.out.println(user);
        System.out.println(pwd);
```
## 創建運行時的類物件
- newInstance
    - 創建對應的運行時類物件
    - 調用無參數的建構方法，該類需要有無參數建構方法且訪問權限

```java=
/**
 * 創建指定類物件
 * */
public Object getInstance(String classPath) throws Exception{
    Class cl = Class.forName(classPath);
    return cl.newInstance();
}
```

## 獲取運行時類的完整結構
```java=
 @Test
    public void test(){
        Class cl = Person.class;
        // 獲取當前運行時類和其父類中 public 權限的屬性
        Field[] fields = cl.getFields();
        for(Field f : fields) {
            System.out.println(f);
        }
        System.out.println();
        // 獲取當前運行時類中所有屬性
        Field[] fields1 = cl.getDeclaredFields();
        for(Field f : fields1) {
            System.out.println(f);
        }

    }
    @Test
    public void test1() {
        Class cl = Person.class;
        Field[] fields = cl.getDeclaredFields();
        for(Field f : fields) {
             // 權限修飾
            System.out.print(Modifier.toString(f.getModifiers()));
            // 數據類型
            System.out.print(f.getType().getName());
            // 變量名
            System.out.print(f.getName());
        }
```


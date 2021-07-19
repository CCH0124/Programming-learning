# IO Stream

## File 操作

```java
new File(String pathname);
new File(File parent, String child);
new File(String parent, String child);
```

```java
package com.example.cch.day01;

import java.io.File;
import java.io.IOException;

public class FileTes {
    public static void main(String[] args) throws IOException {
        createMethod1();
        createMethod2();
        createMethod3();
    }

    private static void createMethod1() throws IOException {
        String filePath = "C:\\Users\\ASUS\\Documents\\Programming-train\\JAVA\\io\\src\\main\\java\\com\\example\\cch\\resource\\createMethod1.txt";
        File file = new File(filePath);

        // 建立檔案，與硬碟交互
        file.createNewFile();
    }

    private static void createMethod2() throws IOException {
        File parentFile = new File("C:\\Users\\ASUS\\Documents\\Programming-train\\JAVA\\io\\src\\main\\java\\com\\example\\cch\\resource\\");
        String fileName = "createMethod2.txt";

        File file = new File(parentFile, fileName);
        // 建立檔案，與硬碟交互
        file.createNewFile();
    }

    private static void createMethod3() throws IOException {
        String parent = "C:\\Users\\ASUS\\Documents\\Programming-train\\JAVA\\io\\src\\main\\java\\com\\example\\cch\\resource\\";
        String fileName = "createMethod3.txt";
        File file = new File(parent, fileName);
        file.createNewFile();

    }
}

```

## 獲取檔案相關訊息

```java
public class FileInfo {
    public static void main(String[] args) {
        File file = new File("src/main/java/com/example/cch/resource/createMethod1.txt");
        System.out.println("File name: " + file.getName());
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Get Parent: " + file.getParent());
        System.out.println("Exist: " + file.exists());
        System.out.println("Is File: " + file.isFile());
        System.out.println("Is Dir: " + file.isDirectory());
        System.out.println("File size: " + file.length());
    }
}
// File name: createMethod1.txt
// Absolute Path: C:\Users\ASUS\Documents\Programming-train\JAVA\io\src\main\java\com\example\cch\resource\createMethod1.txt
// Get Parent: src\main\java\com\example\cch\resource
// Exist: true
// Is File: true
// Is Dir: false
// File size: 0

```
- mkdir()
    - 建立目錄
- mkdirs()
    - 建立多層級目錄
- delete()
    - 刪除目錄或檔案

## IO Stream 原理以及 Stream 分類
1. IO 處理設備之間的傳輸
1. Java 是以 stream 的方式進行數據的交互

>I 表示 Input，從 Disk、DVD 等儲存設備讀取資料到記憶體，O 表示 Output 從記憶體讀資料至 Disk 等儲存設備

### Stream 分類
- 數據單位不同
    - 8 bit 
        - 字節流
            - 非文本數據
            - 圖片
            - 影像
    - 16 bit
        - 字符流
            - 文本
- stream 方向
    - input
    - output
- stream 的角色
    - 節點 stream
    - 處理 stream

||字節流|字符流|
|---|---|---|
|input|InputStream|Reader|
|output|OutputStream|Writer|

> 都為抽象類不能實例化

![](https://img-blog.csdn.net/20170915145559782?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanNzZ190enc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## FileInputStream 和 FileOutputStream

- FileInputStream
    - 檔案輸入流
    - 使用 `read()` 方法，單個字節讀取效率低
    ```java
    int read = 0;
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
           while ((read = fileInputStream.read()) != -1) {
                System.out.print((char)read);
           }
        } 
    ```
    - 使用 `read(byte[] byte)` 方式效率會提高
    ```java
    int readLen = 0;
        byte[] buff = new byte[8]; // 一次讀取 8 個字節
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
        // fileInputStream.read(buff) 回傳是讀到字節的長度
           while ((readLen = fileInputStream.read(buff)) != -1) {
                System.out.print(new String(buff, 0, readLen));
           }
        } 
    ```
 
- FileOutputStream
    - 在目錄存在下，如果檔案不存在會自動創建
    ```java
    private static void writeFile() throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/FileOutputStreamTest.txt";
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write('a');
            String s = "Hello World~~";
            fileOutputStream.write(s.getBytes());
            fileOutputStream.write(s.getBytes(), 0, 4);
        }
    }
    ```
    - 預設是會覆蓋檔案，如果要覆加需設置`FileOutputStream(filePath, true)`，第二個參數用來決定是否以覆加方式進行
    
覆製檔案範例
```java
public class FileCopyImage {
    public static void main(String[] args) {
        String srcFilePath = "src/main/java/com/example/cch/resource/6422.png";
        String dstFilePath = "src/main/java/com/example/cch/resource/image/6422.png";

        byte [] buff = new byte[1024];
        int readLine = 0;
        try (FileInputStream fileInputStream = new FileInputStream(srcFilePath);
                FileOutputStream fileOutputStream = new FileOutputStream(dstFilePath)) {
                while((readLine = fileInputStream.read(buff)) != -1) {

                    fileOutputStream.write(buff, 0, readLine); // 避免檔案損失
                }
                System.out.println("Copy success~");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## FileReader 和 FileWriter
- FileReader
    - 以字符來讀取，非字節

使用範例
```java
private static void readerMethod1() {
        String filePath = "src/main/java/com/example/cch/resource/withoutYou.txt";
        int data = 0;
        try (FileReader fileReader = new FileReader(filePath)) {
            // 單個字符讀取
            while ((data = fileReader.read()) != -1) {
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readerMethod2() {
        String filePath = "src/main/java/com/example/cch/resource/withoutYou.txt";
        int readLine = 0;
        char[] buff = new char[8];
        try (FileReader fileReader = new FileReader(filePath)) {
            // 單個字符讀取
            while ((readLine = fileReader.read(buff)) != -1) {
                System.out.print(new String(buff, 0, readLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

- FileWriter
    - 輸出的檔案可以不存在，會自動創建
    - 當使用 FileWriter 可針對 append 參數設定，是否覆蓋檔案

```java
public class FileWriterTest {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/example/cch/resource/FileWriter.txt";
        char [] chars = {'a', 'b', 'c'};
        try (FileWriter fileWriter = new FileWriter(filePath, true)) { // true 表示覆加
            fileWriter.write('A');
            fileWriter.write(chars);
            fileWriter.write("Hello World!".toCharArray(), 0, 5);
            fileWriter.write("str");
            fileWriter.write("str", 0, 2);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}
```

>FileWriter 使用後，需關閉或刷新（這些動作才能將資料從記憶體寫入製至持久儲存的地方），否則寫入不到指定的檔案

## 節點流和處理流
- 節點流可以從一個特定的數據源**讀寫數據**，如 `FileReader`、`FileWriter`
- 處理流（或叫包裝流）是**連接**以存在的流，為程序提供更強大的讀寫功能，如 `BufferedReader`、`BufferedWriter`
    - 以 buffer 方式提高輸入輸出效率

![](https://i.imgur.com/0k1MQZk.png)

>他們使用了裝飾模式封裝了節點流（FileReader etc.）
### BufferedReader 和 BufferedWriter
使用 `readLine` 方式，按行讀取

```java
public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/withoutYou.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        String line; // 按行讀取
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        bufferedReader.close();
    }
}
```

BufferedWriter 範例，使用覆加模式時是定義在 `FileWriter`，可透過
`newLine()` 方法換行。

```java
public class BufferedWriterTest {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/BufferedWriterTest.txt";

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        bufferedWriter.write("Hello");
        bufferedWriter.newLine(); // 換行
        bufferedWriter.write(123); // 轉 ascii
        bufferedWriter.write("World", 0, 3);

        bufferedWriter.close();
    }
}
```

複製檔案範例

`BufferedReader` 和 `BufferedWriter` 針對於*字符*操作，否則讀取二進制檔案會有遺失可能。

```java
public class BufferedCopyWithoutYou {
    public static void main(String[] args) {
        String srcFilePath = "src/main/java/com/example/cch/resource/withoutYou.txt";
        String dstFilePath = "src/main/java/com/example/cch/resource/withoutYou_use_buffered_copy.txt";

        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFilePath)); 
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dstFilePath))) {
                    while ((line = bufferedReader.readLine()) != null) {
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                    }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## BufferedInputStream 和 BufferedOutputStream

讀取圖片和聲音範例，但在根本上它也可以處理字符相關操作

```java
public class BufferedStreamCopyMusic {
    public static void main(String[] args) {
        String srcFilePath = "src/main/java/com/example/cch/resource/Lucid Dreamer.mp3";
        String dstFilePath = "src/main/java/com/example/cch/resource/music/BufferedStreamCopyMusic_Lucid Dreamer.mp3";

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcFilePath));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dstFilePath))) {
                byte [] buff = new byte[1024];
                int readLine = 0;

                while((readLine = bufferedInputStream.read(buff)) != -1 ){
                    bufferedOutputStream.write(buff, 0, readLine);
                }

        } catch (IOException e) {
            e.printStackTrace();
        }

        File src = new File(srcFilePath);
        File dst = new File(dstFilePath);
        System.out.println(src.length() == dst.length());
    }
}
```

## ObjectInputStream 和 ObjectOutputStream

將物件進行序列化(ObjectOutputStream)和反序列化(ObjectInputStream)操作。序列化就是保存數據的*值*和*類型*，反序列化就是恢復它們成一個物件。

要讓某個物件支持序列化機制，則必須讓該類是可序列化的，因此必須實現以下其中一個介面

- Serializable
    - 沒有任何方法
- Externalizable


將 Dog 物件序列化範例，範例還針對 int、boolean 類型進行序列化
```java
public class ObjectOutputStreamTest {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/data.dat";

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        objectOutputStream.writeInt(100);
        objectOutputStream.writeBoolean(true);
        objectOutputStream.writeChar('a');
        objectOutputStream.writeUTF("火影");
        objectOutputStream.writeObject(new Dog("柴柴", 2));

        objectOutputStream.close();

        
    }
}

```

反序列化範例，當要使用 Dog 物件時需要進行向下轉型，而當前類需要有權限存去該 Dog 類。

- 反序列化過程要和序列化生成過一致
- static、transient 的成員不會被序列化
- 序列化具備可繼承性，某類實現序列化，則它所有子類會默認實現序列化
```java
String filePath = "src/main/java/com/example/cch/resource/data.dat";

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readUTF());
        // 編譯時是 Object 類型，運行時是 Dog 類型
        Object dog = ois.readObject();
        System.out.println(dog.getClass());
        System.out.println(dog);

        Dog dog2 = (Dog)dog;
        System.out.println(dog2.getName());
        ois.close();
```

## 標準輸入輸出流
- System.in
    - InputStream
    - 鍵盤
- System.out
    - PrintStream
    - 螢幕

## InputStreamReader 和 OutputStreamWriter
- 字節流轉換字符流
- 轉換亂碼問題，因此可以指定編碼格式

```java
public class OutpuStreamTest {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/OutpuStreamTest.txt";
        String charSet = "utf-8";

        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), charSet);
        osw.write("Hello, 你好");
        osw.close();

    }
}
```
# JAVA 異常概述

- 系統運行過程仍然會遇到一些問題，因為有很多問題不能只靠程式碼解決
- 語法錯誤和邏輯錯誤不是異常
- 分為兩類
    - Error
        - StackOverflowError or OutOfMemoryError
        - JVM 系統內部錯誤、資源耗盡
    - Exception
        - 編碼錯誤或偶然的外在因素導致一般性的問題，可使用針對性的編碼做處裡
            - 空指針訪問
            - 讀取部存檔案
            - 網路連接中斷
            - 陣列越界
- 一般有兩種解決方案
    - 遇到錯誤就終止程序運行
    - 一開始就考慮錯誤的檢測、錯誤訊息提示以及錯誤的處裡
- 捕獲錯誤最理想的是在**編譯期間**，但有的錯誤是在**運行**時才出現
- throwable(父類)
    - Error
        - JAM 問題等
    - Exception
        - checked 編譯
            - IOException
                - FileNotFoundExceeption
            - ClassNotFoundException
        - unchecked 運行
            - NullPointerException
            - ArrayIndexOutOfBoundsException
            - ClassCastException
            - etc

![](https://i.imgur.com/zyiMmsn.png )
![](https://img2018.cnblogs.com/blog/1099419/201902/1099419-20190223224630614-346698576.png)

## try-catch-finally
- 用 if-else 處裡一些異常時，代碼會有過長、可讀性差等問題
- JAVA，是將一長處理的程式代碼集中在一起，與正常的程式碼分開，使得簡潔、優雅、易維護
- 異常處理方式
    - try-catch-finally
    - throws+異常類型 
- 抓拋模型
    - 拋
        - 程式在正常執行過程中，一旦出現異常，就在異常代碼生成一個對應異常對象，並將此對象拋出
        - 拋出對象後，其後程式碼不再執行
    - 抓
        - 異常處裡的方式
            - try-catch-finally
            - throws
### try-catch-finally
- 使用 try 將可能出現的異常包裝起來，一旦發生異常，就會產生一個異常類，去 catch 中進行匹配
- 當匹配到某一個 catch 時，就進入該 catch 中處裡異常，處裡完後，就跳出當前 try-catch 結構(沒 finally 情況)，繼續執行其後代碼。
- catch 中異常類型有子父類關係，**則要求子類在父類上面**
- 常用異常對象處理方式
    - getMessage()
    - printStackTrace()
- try 結構中聲明的變量，在出了 try 結構後，不能再使用
```java=
String str = "123";
str = "abc";
try{
    int num = Integer.parseInt(str);
}catch(NumberFormatException){
    System.out.println("...");
}
```

- finally 中聲名的是一定會被執行的程式碼，即使 catch 中又出現異常，try 或 catch 中有 return 語句
- finally 中還可以再包 try-catch
- 編譯異常通常要 try-catch，運行時異常可省略

### throws
- 寫在方法宣告處，指名此方法執行時，可能會拋出異常類型
    - 一旦該方法被執行時出現異常，該對象滿足 throws 後異常類型時，就會被拋出
    - 異常代碼後的代碼，就不再執行
- 只是將異常拋給了方法的調用者，並沒將異常處理掉
- 如果父類中被重寫的方法沒有 throws 方式處裡異常，則子類重寫的方法也不能用 throws，表示如果子類重寫的方法有異常，必須使用 try-catch-finally 方式處裡
- 執行的方法 a 中，先後又調用了另外的幾個方法，這個方法是透過遞進關係執行
    - 建議使用 throws 方式進行處裡，而執行的方法 a 可以考慮 try-catch-finally 方式進行處裡
### override 異常拋出規則
- 子類重寫的方法拋出的異常類型不大於父類被重寫的方法拋出的異常類型

### 手動拋出異常
異常的產生
- 系統自動生成
- 手動生成，並 `throw`

通常以，以下為主
```java=
throw new RuntimeException();
throw new Exception();
/**
 * throws 異常處理
 * throw 生常異常物件，在方法內
 * /
```

## 用戶自定義異常類
屬於拋動作
1. 繼承於現有的異常結構
- RuntimeException
- Exception
2. 提供 serialVersionUID
3. overload 建構方法


世界上最遙遠的距離，是我在 if 裡你在 else 裡，似乎一值相伴卻永遠分離
世界上最癡心的等待，是我當 case 你是 switch，或許永遠都選不上自己
世界上最真情的相依，是你在 try 我在 catch。無論你發什麼脾氣，我都默默承受，靜靜處裡。到那時，在來期待我們的 finally。
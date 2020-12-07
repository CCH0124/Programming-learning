# JAVA 常用類
## 字串相關
### String
- final 類
- 值創建後，不能再更改
    - 需要重新指定記憶體位置賦值，當現有字串進行操作時也是
- String 物件字符內容除存在 `char value[]` 中
- 會存在至字串池對於常量
    - heap 中 1.7 版，1.8 在方法區的元空間
    - 其中該池不會有重複值
    - string 和 new String() 存放記憶體位置有差異

```java=
String s = new String("abc");
// 創建兩個物件，一個為 heap 中 new，一個是 char[] 對應常量池數據 "abc"
```
- 常量和長量拼接結果在常量池
- 只要其中有一個變量，結果就在 heap 中
- 拼接調用 `intern()`，返回值在常量池中

```java=
final String s4= "JAVA"; // 在常量池，因為 final
```
#### String to byte
- getBytes()
### StringBuffer StringBuilder 
- StringBuffer
    - 是可變字符序列
    - 執行續安全，效率低
- StringBuilder 
    - 是可變字符序列
    - 非執行續安全，效率高
- String 
    - 是不可變字符序列

```java=
String str = new String(); // char[] value = new char[0]
String str1 = new String("abc")// char[] value = new char{'a', 'b', 'c'}

StringBuffer sb1 = new StringBuffer(); // char[] value = new char[16]; 低層預設創建長度
sb1.append('a');// value[0] = 'a'
sb1.append('b');// value[1] = 'b'
StringBuffer sb1 = new StringBuffer("abc"); // char[] value = new char["abc".length+16];
System.out.println(sb2.length()); // 3，用 count 方式
```
## JDK8 前日期時間 API
## JDK8 中日期時間 API
## JAVA 比較
### Comparable interface
- 自然排序
### Comparator interface
- 自定排序
- 適用於臨時使用

## System
- 私有建構方法
- 內部成員和方法都是靜態
## Math
- BigInteger
    - 表示不可變的任意精度的整數
- Decimal
    - float 和 double，精度較高
## JAVA 基礎
### 變數分類

定義變數

```java=
// DataType varName = varValue;
String s = "Test";
```

### 數據分類

- primitive Type
    - 數值
        - byte
        - short
        - int
        - long
        - char
        - boolean

- reference Type
    - Class
        - String 屬於 class
    - interface
    - Array

|類型|儲存空間|範圍|
|---|---|---|
|byte|1 Byte（8 bit）|$128$ ~ $127$|
|short|2 Byte（8 bit）|$-2^{15}$ ~ $2^{15}-1$|
|int|4 Byte（8 bit）|$-2^{31}$ ~ $2^{31}-1$|
|long|8 Byte（8 bit）|$-2^{63}$ ~ $2^{63}-1$|
|float|4 Byte（8 bit）|$-3.403E38$ ~ $3.403E38$|
|double|8 Byte（8 bit）|$-1.798E308$ ~ $1.798E308$|
|char|2 Byte(1 字符)||
|boolean|||
>char 使用 Unicode 來表示

### 數據類型之間運算
- 自動類型提升
    - 當不同型別做運算時，會以佔 Byte 數多的類型為提升目標
        - char 和 short 與 byte 需要使用 int 做接收
- 強制類型轉換
    - 精度遺失

##### Example
```java=
System.out.println(3+4+"H"); // 7H
System.out.println("H"3+4); // H34
System.out.println('a'+1+"H");//98H
```

```java=
short s = 5;
s = s - 2; // Error
byte b =3;
b = b + 4; // Error
b = (byte)(b+4); // Ok
char c = 'a';
int i = 5;
float d = .314F;
double result = c + i + d; // Ok
byte b = 5;
short s = 3;
short t = s + b // Error
```

### 進制
- 二(binary)
    - 0, 1
- 十(decimal)
    - 0-9
- 八(octal)
    - 0-7
- 十六(hex)
    - 0-9 和 A-F


|decimal|hex|octal|binary|
|---|---|---|---|
|0|0|0|0|
|1|1|1|1|
|2|2|2|10|
|3|3|3|11|
|4|4|4|100|
|5|5|5|101|
|6|6|6|110|
|7|7|7|111|
|8|8|10|1000|
|...|...|...|...|

```java=
int binary = 0b110;// 6
int decimal = 110;// 110
int octal = 0127;// 87
int hex = 0x110A;// 4362
```

### 名稱命名規範
- package
    - 小寫
- Class、interface
    - 所有單字首字大寫
- variable、method
    - 第一個單字首字小寫，第二之後為大寫
- Static
    - 全大寫


### 運算符號
- 算數
    - +\-\*/%
- 賦值
    - =
    - +=、-=、\*=、%=
- 比較
    - ==
    - !=
    - <
    - >
    - ...
    - instanceof
- 邏輯
    - & 和 |
- 位元
    - 二進制運算
    - <<
    - >>
    - >>>
    - &
    - |
    - ^
    - ~

```java=
3<<2 = 12 // 3*2*2
3>>1 = 1 // 3/2
3>>>1 = 1 // 3/2
6&3 = 2
6|3 = 7
6^3 = 5
~6 = -7
```
- 三位元
    - () ? :

### Scanner

從鍵盤獲取不同類型的值

```java=
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
int num = sc.nextInt();
```


### this 關鍵字 
- 方法內部使用，及這個方法所屬對象的引用
- 在建構方法中，表示該建構方法正在初始化對象
- this 表示當前物件，可調用類的屬性、方法、建構方法
- 在方法內需要用到調用該方法物件時，就使用 this

```java=
class Person {
    private String name;
    private int age;
    /**
     * 如果為 name = name，則 name 是賦值給自己
     * */
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getAge() {
        return age;
    }
}
```

`this()` 調用建構方法，會對照參數尋找對應建構方法



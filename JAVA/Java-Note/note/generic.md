# 泛型

## 為什麼要有泛型
所謂泛型，就是允許在定義*class*、*interface* 時透過一個標示表示類鍾某個屬性的類型或者維某方法的迴船值和參數類型。該類型參數在使用時確定。
##### 為什麼不用 Object
- 有了泛型解決元素儲存的安全性問題
    - Object 導致類型不一致
- 獲取數據時，需要類型強轉換問題
    - 強轉可能會有 `ClassCastException`
## 在集合中使用泛型
## 自定義泛型結構
- 繼承泛型的類時，繼承時指名泛型類型，則該子類不須再給泛型類型，但也可以宣告成泛型類
    - 子類不保留父類的泛型
    - 子類保留父類的泛型
    
```java=
class Father<T1, T2> {

}
// 無類型，擦拭，等價 Father<Object, Object>
class Son1 extends Father {

}
// 具體類型
class Son2<A, B> extends Father<Integer, String> {

}
// 全部保留
class Son3<T1, T2> extends Father<T1, T2>{

}
// 部分保留
class Son4<T2> extends Father<Integer, T2> {

}
```
- 也可多個泛型類參數 
- 泛型不同引用不能相互賦值
    - `<Interger>` 不能賦值給 `<String>`
- 泛型如果不指定，將被擦拭，泛型對應的類均按照 `Object` 處理，*但不等價於 Object*
- 如果泛型結構是一個 `interface` 或 `abstract`，則不可創建泛型類的物件
- 泛型類只能用封裝類
- *靜態方法*不能使用類的泛型
    - 因為 class 的加載順序
- `Exception` 不能有泛型
- 不能使用 `new E[]`

### 泛型方法
- 在方法中出現泛型結構，泛型參數與類的泛型參數沒有任何關係。泛型方法所屬的類與泛型類都沒有關係。
- 可用*靜態方法*，因為在調用時才決定類型，並非時例化
```java=
public <E> List<E> copyFromArrayToList(E[] arr) {
    ArrayList<E> list = new ArrayList<>();
    for (E e : arr) {
        list.add(e);
    }
    return list;
} 
```

## 泛型在繼承上的體現
- class A 是 class B 父類，`G<A>`和`G<B>`不具備子父類關係，兩者是並列關係
## 通配符的使用
-  class A 是 class B 父類，`G<A>`和`G<B>` 無關係，兩者共同父類是 `<?>`
-  對於 `List<?>` 就不能向其內部添加數據，`null` 他是所有類型成員是一個例外，可讀取數據型態為 `Object`
- `<? extends A>`
    - `G<? extends A>` 可以做為 `G<A>` 和 `G<B>` 的父類，其中 B 是 A 的子類
    - ? 要是 A 的子類
- `G<? super A>`
    - `G<? super A>` 可以做為 `G<A>` 和 `G<B>` 的父類，其中 B 是 A 的父類
```java=
List<Object> list1 = null;
List<String> list2 = null;
List<?> list = null; // ? 通配符

// 不會因為子父類關係，而並列
list = list1;
list = list2;

Iterator<?> iterator = list.iterator();
while(iterator.hasNext()){
    Object obj = iterator.next(); // Object 是根父類
    System.out.Println(obj);
}
```

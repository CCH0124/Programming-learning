## variable 類型
### 自定義
當前 `shell` 有效。

定義
```shell
var=value # 等號不能有空格，var 不能以數字開頭
readonly B=3 # 唯讀，無法 unset
```

引用
```shell
$var
${var}
```

查看
```shell
echo $var
set
```

撤銷
```shell
unset var
```
### 環境
當前或子 `shell` 有效

把變數提升為全域環境變數，可供給給其他 shell 使用
```shell
export var_name
```
### 位置變量
- $n
    - n 為數字
    - `$0` 表示該 shell 檔案名稱
    - `$1~$9` 表示第一到第九的參數
    - 10 個以上需要大括號，`${10}`
- $#
    - 獲取所有輸入參數個數，常與循環搭配
- $*
    - 代表命令行中所有的參數，`$*` 把所有參數視為一個整體
- $@
    - 代表命令行中所有的參數，`$@` 把每個參數區分
- $?
    - 上個命令的退出狀態，或函數的返回值
    - 值為 0，表示上一個命令正確執行
    - 非 0，表示執行不正確
- $$ 
    - 當前 Shell process ID，對於 Shell 腳本，就是這些腳本所在的進程 ID
- $!
    - 上一個後台行程 PID
    
## read
|選項|描述|
|---|---|
|-p "message"|提示訊息|
|-a arr|指定 arr 為陣列變數|
|-t number|限制秒數|
|-r|讀取資料，預設會過濾跳脫字元，設定 -r 即可保留|
|-n|限制輸入長度|


雙引號("")內的特殊字元可以保有變數特性， 單引號('')內的特殊字元則僅為一般字元。

## 變量內容替換和刪除
- \# 從前往後
- %  從後往前

```shell
url=www.google.com
echo ${#url} # 14
echo ${url#www} # 截斷 www
.google.com
echo ${url#*.}  # 最短匹配
google.com
echo ${url##*.} # 貪婪匹配，保留不匹配的
com
```

替換
```shell
echo ${url/n/N}  
echo ${url//n/N} # // 每個匹配都執行
#{VARIABLE_NAME-NEW_VALUE} 變量有被賦值或空值，不會起作用
```

## 索引切片
```shell
echo ${url:0:5}
echo ${url:5}
```

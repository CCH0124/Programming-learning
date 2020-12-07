## sed
檔案內容不會改變

|參數|功能|
|---|---|
|-e|直接在指令模式上進行 sed 的動作編輯|
|a|新增，在下一行出現該新增的資料|
|d|刪除|
|s|查找並替換|
|p|打印|
|g|替換一行中所有出現的模式|


```shell
$ sed "3a QWEQWE" a.txt # 從第三行插入
a
b
c
QWEQWE
d
e
f
g
t
h
j
k
l
s
s
$ sed "/s/d" a.txt
a
b
c
d
e
f
g
t
h
j
k
l
$ sed "s/d/ddd/g" a.txt
a
b
c
ddd
e
f
g
t
h
j
k
l
s
s
$ sed 1d  a.txt
b
c
d
e
f
g
t
h
j
k
l
s
s
```
##### 參數 \&
在某些情況下，可能希望搜索模式並通過向其添加一些額外字符來替換該模式。在這種情況下，派上用場。＆表示匹配的字符串。

```shell=
sed 's/thy/{&}/Ig'
# 將 thy 換成 {thy}，I 不分大小寫，g 一行中所有匹配的模式都替換
```

##### 參數 I
不分大小寫

##### 參數 g
替換一行中所有出現的模式
##### 替換行中第 n 次出現的模式
```shell=
sed 's///1' # 第一次匹配
```

## introduction
### &
在某些情況下，可能希望搜索模式並通過向其添加一些額外字符來替換該模式。在這種情況下，派上用場。＆表示匹配的字符串。
```shell
sed 's/thy/{&}/Ig'
# 將 thy 換成 {thy}，I 不分大小寫，g 一行中所有匹配的模式都替換
```
### I
不分大小寫

### g
替換一行中所有出現的模式

### 替換行中第 n 次出現的模式
```shell
sed 's///1' # 第一次匹配
```
## Ref
[1](http://www.grymoire.com/Unix/Sed.html#uh-10a)
[2](http://tldp.org/LDP/abs/html/x23170.html)
[3](http://www.folkstalk.com/2012/01/sed-command-in-unix-examples.html)
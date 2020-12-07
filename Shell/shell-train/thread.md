## 執行續

後台執行
```shell
{
...
...
}& # 表示後台執行
wait # 等待所有後台執行完成，要確認當前後台程式碼都執行完成
```

### File Descriptors（FD）

![](https://i.imgur.com/nOIR5B5.png)

行程使用 FD 來管理打開的檔案。
```shell
$ ls /proc/$$/fd
```

打開方式
```shell
$ exec 6<> /file # 打開檔案
$ exec 6<&- # 釋放 FD
# 如果未釋放，則 FD 還是存在的，刪除原檔案不會造成影響
```
### 管道

![](https://i.imgur.com/UNFrVXD.png)

使用後就不存在了，`|` 該關鍵字符是匿名管道，`mkfifo` 或 `file` 等都是命名管道


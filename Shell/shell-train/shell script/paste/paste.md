檔案列和列合併
- -d
    - 指定的間隔字符取代跳格字符
- -s
    - 將一個文件中的多行數據合併為一行進行顯示。
- \- 
    - 如果 file 部分寫成 - ，表示來自 standard input 的資料的意思。
```shell=
$ paste data1 data2 # data1 每一列和 data2 每一列合併，預設 TAB 分隔
$ paste -d'#' data1 data2 # # data1 每一列和 data2 每一列合併，用 # 分隔
```
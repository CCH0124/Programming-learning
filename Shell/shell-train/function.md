## function
- 傳參數 `$1`、`$2` 等
- 變量 `local`
- 返回值 `$?`

如果再無 `return` 情況下用 `$?` 接值會是函數最後一行代碼執行的狀態碼。在 shell 的返回值範圍是 `0~255`。就是，函數返回值，只透過 `$?` 獲得，可用 return 回傳（0 ~ 255），否則會以最後一行命令運行結果作為返回值。


```shell
function_name (arg1, arg2) {
}
function name () {
}
```

## return
```shell
fun2(){
  return $[2*$1]
}

result=$(fun2 10)
```

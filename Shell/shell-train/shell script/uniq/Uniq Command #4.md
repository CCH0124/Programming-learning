## problem
[uniq-command-4](https://www.hackerrank.com/challenges/text-processing-in-linux-the-uniq-command-4/problem)

## code
```shell
uniq -u 
# 沒有 BEGIN 開頭預設第二行生效
# 遇到 new delhi 這字串時，空白被取代 : 導致 $3 輸出 new 而已
# uniq -c | tr -s '^[[:blank:]]' ':' | awk 'BEGIN{FS=":"} $2 == 1 {print $3}' error
```
```shell
# uniq -c | tr -s '^[[:blank:]]' ' ' | sed 's/[[:blank:]]/:/2'| awk 'BEGIN{FS=":"} $1 == 1 {print $2}'
```
```shell
# uniq -c | sed 's/[[:blank:]]*/:/2'| awk 'BEGIN{FS=":"} $1 == 1 {print $2}'
```
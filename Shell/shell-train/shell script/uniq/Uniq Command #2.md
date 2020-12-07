## problem
[uniq-command-2](https://www.hackerrank.com/challenges/text-processing-in-linux-the-uniq-command-2/problem)

## code
```shell
# [:blank:] 不知道為何不行
uniq -c | sed 's/^[[:blank:]]*//g'
# tr 第二參數不能為空
# uniq -c | tr -s '^[[:blank:]]*' ''
```
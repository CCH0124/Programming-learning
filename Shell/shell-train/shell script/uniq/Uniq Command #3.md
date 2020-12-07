## problem
[uniq-command-3](https://www.hackerrank.com/challenges/text-processing-in-linux-the-uniq-command-3/problem)

## code

```shell
# i 忽略大小寫
uniq -ci | sed 's/^[[:blank:]]*//g'
```
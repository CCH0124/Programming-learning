## problem
[Getting started with conditionals](https://www.hackerrank.com/challenges/bash-tutorials---getting-started-with-conditionals/problem)

## code
```shell
read str
test $str == "Y" || test $str == "y" && echo "YES"
test $str == "N" || test $str == "n" && echo "NO"
```
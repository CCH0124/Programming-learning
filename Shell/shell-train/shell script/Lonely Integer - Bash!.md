## problem
[Lonely Integer - Bash!](https://www.hackerrank.com/challenges/lonely-integer-2/problem)

## code
```shell
read N
read -a arr
echo ${arr[*]} | xargs -n 1 | sort | uniq -u
```
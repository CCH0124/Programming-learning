## problem
[grep B](https://www.hackerrank.com/challenges/text-processing-in-linux-the-grep-command-5/problem)
## code
```shell
 grep -E '([0-9]) *\1'
```
```shell
grep -E '([[:digit:]]) *\1'
```
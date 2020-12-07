## problem
[Middle of a Text file](https://www.hackerrank.com/challenges/text-processing-in-linux---the-middle-of-a-text-file/problem)

## code
```shell
head -n 22 | tail -n 11
```
```shell
cut -d$'\n' -f 12-22
```
```shell
sed -n 12,22p
```
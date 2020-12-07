## problem
[Slice an Array](https://www.hackerrank.com/challenges/bash-tutorials-slice-an-array/problem)

## code
```shell
declare -a arr
i=0
while read LINE
do
    arr[$i]=$LINE
    let i++
done

echo ${arr[@]:3:5}

```

```shell
head -8 | tail -5| paste -sd ' '
```
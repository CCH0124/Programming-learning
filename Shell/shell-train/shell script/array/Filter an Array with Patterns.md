## problem
[Filter an Array with Patterns](https://www.hackerrank.com/challenges/bash-tutorials-filter-an-array-with-patterns/problem)

## code
```shell
declare -a arr
i=0
while read LINE
do
    arr[$i]=$LINE
    let i++
done
declare -a patter=(${arr[@]/*[aA]*/})
echo ${patter[@]}
```

```shell
grep -iv "a"
```

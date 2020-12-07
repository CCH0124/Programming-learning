## problem
[Concatenate an array with itself](https://www.hackerrank.com/challenges/bash-tutorials-concatenate-an-array-with-itself/problem)

## code
```shell
arr=($(cat))
re=("${arr[@]}" "${arr[@]}" "${arr[@]}")
echo ${re[@]}
```
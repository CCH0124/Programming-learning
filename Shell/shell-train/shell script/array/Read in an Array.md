## problem
[Read in an Array](https://www.hackerrank.com/challenges/bash-tutorials-read-in-an-array/problem)
## code
```shell
declare -a arry
i=0
while read LINE
do
    arry[$i]=$LINE
    # or (($i+=1))
    let i++
done

echo ${arry[@]}
```

## problem
[Compute the Average](https://www.hackerrank.com/challenges/bash-tutorials---compute-the-average/problem)

## Code
```shell
# read p
# 
# for i in $(seq 1 $p); do
#     read n
#     # sum=$(($sum + $n))
#     let sum=$sum+$n
# done
# let r=$sum/$p
# printf "%.3f\n" $r

read p

for i in $(seq 1 $p); do
    read n
    sum=$(($sum + $n))
done
# let 與 expr 似乎不能做到福點數
r=$(echo $sum/$p | bc -l)
printf "%.3f\n" $r
```
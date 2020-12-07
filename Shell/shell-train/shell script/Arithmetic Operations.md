## problem
[arithmetic-operations](https://www.hackerrank.com/challenges/bash-tutorials---arithmetic-operations/problem)

## Code
```shell
read exp

#echo "scale=3; $exp" | bc -l

printf "%0.3f" $(echo "$exp" | bc -l)

```
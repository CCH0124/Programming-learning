## problem
[Comparing Numbers](https://www.hackerrank.com/challenges/bash-tutorials---comparing-numbers/problem)

## code
```shell
function compare(){
    local num1=$1
    local num2=$2

    if [ $1 -gt $2 ]; then
        echo -e "X is greater than Y"
    elif [ $1 -eq $2 ]; then
        echo -e "X is equal to Y"
    else
        echo -e "X is less than Y"
    fi
}

read X
read Y
compare $X $Y
```

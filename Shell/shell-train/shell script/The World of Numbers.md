## problem
[the-world-of-numbers](https://www.hackerrank.com/challenges/bash-tutorials---the-world-of-numbers/problem)
## Code
```shell
function add() {
    local num1=$1
    local num2=$2
    local result
    let result=$num1+$num2
    echo $result
}
function sub() {
    local num1=$1
    local num2=$2
    local result
    let result=$num1-$num2
    echo $result
}
function mul() {
    local num1=$1
    local num2=$2
    local result
    let result=$num1*$num2
    echo $result
}
function div() {
    local num1=$1
    local num2=$2
    local result
    let result=$num1/$num2
    echo $result
}

read num1
read num2

add $num1 $num2
sub $num1 $num2
mul $num1 $num2
div $num1 $num2
#Error
# declare -i add=$num1+$num2
# echo ${add}
# declare -i sub=$num1-$num2
# echo ${sub}
# declare -i mul=$num1*$num2
# echo ${mul}
# declare -i div=$num1/$num2
# echo ${div}

```
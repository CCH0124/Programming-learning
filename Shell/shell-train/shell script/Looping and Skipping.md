## problem
Your task is to use for loops to display only odd natural numbers from  to .

### Input Format

There is no input.

Constraints

\-

### Output Format
```shell
1
3
5
.
.
.
.
.
99  
```
### Sample Input

\-

### Sample Output
```shell
1
3
5
.
.
.
.
.
99  
```
### Explanation

\-

## Code
```shell
echo $(seq -s"\n" 1 2 99)
```

```shell
num=1
max=100
while [ $num -lt $max ] 
do
    echo $num
    num=$(($num + 2))
done
```

```shell
for item in $(seq 1 2 99)
do
    echo $item
done
```



## problem
Use for loops to display the natural numbers from  to .

### Input Format

There is no input

Constraints

### Output Format
```shell
1
2
3
4
5
.
.
.
.
.
50
```
### Sample Input

### Sample Output

\-

Explanation

\-

## Code
```shell
a=1
max=51
while [ $a -lt $max ]
do
    echo $a
    a=$(($a + 1))
done
```
```shell
a=1
max=50
for (( i=1; i<=$max; i=i+1 ))
do
    echo $i
done

```
## problem
['Awk' - 2](https://www.hackerrank.com/challenges/awk-2/problem)

## code
```shell
awk '{
if ($2 >=50 && $3 >= 50 && $4 >= 50)
    print $1,":","Pass";
else
    print $1,":","Fail";
}'
```
## problem
['Awk' - 4](https://www.hackerrank.com/challenges/awk-4/problem)

## code
```shell
awk '{
    if ( NR%2 == 0 )
        printf $0"\n";
    else
        printf $0";";
}'

```
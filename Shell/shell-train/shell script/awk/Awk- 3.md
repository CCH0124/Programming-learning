## problem
['Awk' - 3](https://www.hackerrank.com/challenges/awk-3/problem)

## code
```shell
awk '{ if( ($2+$3+$4)/3 <= 50) { 
            print $1,$2,$3,$4,": FAIL" 
        } 
        if( ($2+$3+$4)/3 <=80 && ($2+$3+$4)/3 >= 60) { 
            print $1,$2,$3,$4,": B" 
        } 
        if( ($2+$3+$4)/3 <=90 && ($2+$3+$4)/3 > 80) { 
            print $1,$2,$3,$4,": A" }  
        }'  
```
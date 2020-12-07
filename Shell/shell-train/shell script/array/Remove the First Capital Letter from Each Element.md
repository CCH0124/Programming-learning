## problem
[remove-the-first-capital-letter-from-each-array-element](https://www.hackerrank.com/challenges/bash-tutorials-remove-the-first-capital-letter-from-each-array-element/problem)

## code
```shell
arr=($(cat))
# [:upper:]
echo ${arr[@]/#[A-Z]/.} 
```
[ref](https://wiki.bash-hackers.org/syntax/pe#search_and_replace)
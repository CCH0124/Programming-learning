## problem
Write a Bash script which accepts  as input and displays a greeting: "Welcome (name)"

### Input Format

One line, containing a .

### Output Format

One line: "Welcome (name)" (quotation marks excluded). 
The evaluation will be case-sensitive.

### Sample Input 0

Dan  
### Sample Output 0

Welcome Dan  
### Sample Input 1

Prashant
### Sample Output 1

Welcome Prashant

## Code
```shell
welcome() {
    local name="$1"
    echo -e "Welcome "$name
}

read name

welcome $name
```
## introduction
顯示符合樣式的列

- i
    - 不分大小寫
- v
    - 不包含，反向的意思
- l
    - 不顯示符合的列
- n
    - 顯示行號
- r
    - 以遞規方式，搜尋指定目錄下所有檔案

```shell
# grep -nA 10 -e "Installation"  README.md # -e 比對樣式；-A 該樣式找到後，接續 10 行資料顯示
92:## Installation ##
93-
94-Scapy works without any external Python modules on Linux and BSD like operating
95-systems. On Windows, you need to install some mandatory dependencies as
96-described in [the
97-documentation](http://scapy.readthedocs.io/en/latest/installation.html#windows).
98-
99-On most systems, using Scapy is as simple as running the following commands:
100-\`\`\`
101-git clone https://github.com/secdev/scapy
102-cd scapy
```
## Ref
[1](http://www.thegeekstuff.com/2009/03/15-practical-unix-grep-command-examples/)

[2](http://tldp.org/LDP/Bash-Beginners-Guide/html/sect_04_02.html)
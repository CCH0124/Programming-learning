# hackerrank

Shell Script 筆記

1. 自動化系統初始
2. 自動化軟體部署
3. 管理應用程序
4. 日誌分析
5. 自動化備份與恢復
6. 訊息擷取
等等

## 系統變數
- $HOME
    - 家目錄
- $PWD
- $SHELL
- $USER
- $LOGNAME
- etc.

## 快捷鍵
- ctrl+R
- ctrl+D
- ctrl+A
- ctrl+E
- ctrl+L
- ctrl+U
- ctrl+K
  - 刪除該游標後面的字串
- ctrl+S
  - 鎖屏
- ctrl+Q

## 前後台控制
- &
- nohup
  - 終端機關閉後，該執行的命令還會存在
- ctrl+C
- ctrl+Z
- bg
- fg
- kill

## 輸入輸出重新定向
- 0
- 1
  - 鍵盤
- 2
  - 螢幕
- \>
  - 覆蓋
- \>>
  - 追加
- 2>
  - 錯誤輸出
- 2>>
- 2>&1
- &>

## 指令排序
- ;
  - 執行多個命令，不具備邏輯判斷
- &&
  - 依賴前一個指令是否執行成功，失敗不會執行
- ||
  - 依賴前一個指令是否執行成功
  
## 符號
- [符號](https://github.com/CCH0124/hackerrank/blob/master/symbol.md)

## 指令歷史記憶功能
- ![NUMBER]
  - NUMBER 為 history 的行數
- !string
  - 找最近 string 開頭的執行指令
- !$
  - 上個指令最後參數
- !!
  - 執行上個命令
- ^R
  - 搜索歷史指令
## 別名
- alias
- unalias

## 自訂義變數和特殊變數
- [variable](https://github.com/CCH0124/hackerrank/blob/master/variable.md)

## 運算符號
- [operator](https://github.com/CCH0124/hackerrank/blob/master/variable_operator.md)

## 條件判斷
- [conditional](https://github.com/CCH0124/hackerrank/blob/master/conditional.md)

## 流程控制
1. if
```shell
if[ 條件判斷式 ]; then
fi
```
2. case
```shell=
case $變數 in
    "val 1")
        ...
        ;;
    "val 2")
        ...
        ;;
    *)
        ...
        ;;
esac
```

## 系統函數
### basename
```shell=
basename[string/pathname][suffix] # basename 會刪除掉所有的前綴包括最後一個 '/'，然後將字串顯示出來
$ basename /home/cch/client.c
client.c
$ basename /home/cch/client.c .c
client
```

### dirname
檔案絕對路徑，從給定的包含絕對路徑的檔案名中去除檔案名（非目錄部分），然後返回剩下的路徑（目錄的部分）
```shell=
$ dirname /home/cch/client.c
/home/cch
```

## 自訂義函數
- [](https://github.com/CCH0124/hackerrank/blob/master/function.md)

## 系統預設開啟的檔案

系統預設會開啟三個標準檔案
- stdin
    - 0
- stdno
    - 1
- stderr
    - 2
    
## 管線
一個程式的輸出，可以變另一個程式的輸出。

## 執行 shell script
執行時現行的 shell (父)會開啟一個子 shell 環境。

## bash debug
```shell=
sh -v file.sh # 檢查語法
sh -nv file.sh # 不執行，僅查看 code
sh -x file.sh # 追蹤 script 執行
time file.sh # 計算執行時間
```

## login shell
登入主機，執行 bash script 前，是處在 `loging shell` 環境。
每個帳號都可自訂 login shell (adduser user)。那 login shell 定義在 `/etc/passwd` 檔案中。
```shell=
account:UID:GID:User info:home directory:login shell

root:x:0:0:root:/root:/bin/bash
daemon:x:1:1:daemon:/usr/sbin:/usr/sbin/nologin
bin:x:2:2:bin:/bin:/usr/sbin/nologin
```
## 父子 shell
執行 shell 時，會使用 `fork` 開啟一個`子 shell` 程序。子 shell 執行完並不會影響父 shell。
```shell=
假設在這之前目錄是在家目錄
#! /bin/bash
cd /temp
touch test.txt
```
> 使用 `. file.sh` or `source file.sh` 會讓 script 在父 shell 中執行

## 子 shell 再開子 shell
要如何知道自己在哪一層 shell 呢 ?
```shell=
echo $SHLVL
```
查看各 process 階層關係
```shell=
~# ps -axf
  PID TTY      STAT   TIME COMMAND
    1 ?        Ss     0:00 /init ro
    3 tty1     Ss     0:00 /init ro
    4 tty1     S      0:01  \_ -bash
  208 tty1     R      0:00      \_ ps -axf
```
STAT 說明
```shell=
D    Uninterruptible sleep (usually IO)
R    Running or runnable (on run queue)
S    Interruptible sleep (waiting for an event to complete)
T    Stopped, either by a job control signal or because it is being traced.
W    paging (not valid since the 2.6.xx kernel)
X    dead (should never be seen)
Z    Defunct ("zombie") process, terminated but not reaped by its parent.
For BSD formats and when the stat keyword is used, additional characters may be displayed:
<    high-priority (not nice to other users)
N    low-priority (nice to other users)
L    has pages locked into memory (for real-time and custom IO)
s    is a session leader
l    is multi-threaded (using CLONE_THREAD, like NPTL pthreads do)
+    is in the foreground process group
```
## Bash 不同的運作模式
### login
登入主機執行以下步驟
1. 執行 /etc/profile
2. 檢查家目錄
    3. .bash_profile
    4. .bash_login
    5. .profile
### logout
1. 檢查家目錄
    2. .bash_logout
### 執行新 shell
執行非 `login shell`
1. 執行互動式
直接執行 bash，產生子 shell。Bash 會讀取並執行 `/etc/bash.bashrc` 和家目錄 `.bashrc`
2. 執行 shell script(#! /bin/bash)
檢查 Bash ENV 內容，若定義則執行該變數
```shell=
export BASH_ENV="/home/itachi/file.sh"
```
3. sh scrip(#! /bin/sh)
不叫用任何起動檔，無檢查環境變數

### 字符

## 測試真假
在 Bash shell 
- 0
    - true
- 1
    - false

回傳值存在 `$?`
```shell=
~# [ 3 -gt 2 ]
~# echo "$?"
0
```
## 迴圈
預設 `IFS` 分割符是空格

- for
```shell=
for var in con1 con2 con3 ...
do
	程式段
done
```
- while
```shell=

while [ condition ]  <==中括號內的狀態就是判斷式
do            <==do 是迴圈的開始！
	程式段落
done          <==done 是迴圈的結束
```
- until
```shell=
until [ condition ]
do
	程式段落
done
```

## thread

[thread](https://github.com/CCH0124/hackerrank/blob/master/thread.md)

## expect
```shell
#!/bin/expect
USER=$1
IP=$2
PWD=$3
spawn ssh $USER@$IP

expect {
    "yes/no" { send "yes\r"; exp_continue } # "yes/no" 出現的字串 {} 相對應動作
    "password:" { send "$PWD\r" }
}

interact # 與對方互動
```

## shift
位移參數

## shopt
```shell=
#! /bin/bash
shopt -s -o nounset # 強制變數一定要宣告

a=$(ls | grep "sc")
echo $b
```
```shell=
shopt -s nullglob dotglob # nullglob 回傳空字串
                        # dotglob 加入隱藏檔
files=(*)
for f in ${files[@]}
do
        echo $f
done
```
## echo
|Options |Description|
|---|---|
| -n | do not print the trailing newline.|
| -e | enable interpretation of backslash escapes.|
| \b | backspace|
| \\\ | backslash|
| \n | new line|
| \r | carriage return|
| \t | horizontal tab|
| \v | vertical tab|
### "" 與 '' 差異
雙引號內的特殊字元可以保有變數特性，
單引號內的特殊字元則僅為一般字元；
## printf
|||
|---|---|
|%s|字串|
|%b|特殊字元生效|
|%q|特殊字元用 \ 跳脫|
|%(datefmt)T|使用 strftime 格式|
|%c|字元|
|%d|整數|
|%f|浮點數|
|%e|科學記號|
|%i|和 %d 相同|
|%%|顯示 %|

## declare
|||
|---|---|
|declare -a arry|宣告 arry 為陣列|
|declare -A arry|宣告 arry 是關聯式陣列|
|typeset -A arry|宣告 arry 是關聯式陣列|
|declare -i n|宣告 n 是整數|
|declare -f fun|宣告 fun 是函式|
|declare -p|顯示所有的變數名稱和屬性內容|
|declare -F|只顯示函式名稱，但不顯示屬性內容|
|declare -r var|宣告 var 是唯獨變數|
|declare -x PWD="/home/itachi"|宣告 PWD 為環境變數|
|declare -l var|宣告 var 全為小寫字串|
|declare -c var|宣告 var 的第一個字元大寫|
|declare -u var|宣告 var 全為大寫字串|
|declare -n ref|宣告 ref 全其他變數的參考(引用變數)|

## array

```shell=
var[index]=content
```

```shell=
#! /bin/bash 
# To declare static Array  
arr=(prakhar ankit 1 rishabh manish abhinav) 
  
# To print all elements of array 
echo ${arr[@]}        # prakhar ankit 1 rishabh manish abhinav 
echo ${arr[*]}        # prakhar ankit 1 rishabh manish abhinav 
echo ${arr[@]:0}    # prakhar ankit 1 rishabh manish abhinav 
echo ${arr[*]:0}    # prakhar ankit 1 rishabh manish abhinav 
  
# To print first element 
echo ${arr[0]}        # prakhar 
echo ${arr}            # prakhar 
  
# To print particular element 
echo ${arr[3]}        # rishabh 
echo ${arr[1]}        # ankit 
  
# To print elements from a particular index 
echo ${arr[@]:0}    # prakhar ankit 1 rishabh manish abhinav 
echo ${arr[@]:1}    # ankit 1 rishabh manish abhinav 
echo ${arr[@]:2}    # 1 rishabh manish abhinav 
echo ${arr[0]:1}    # rakhar 
  
# To print elements in range 
# prakhar ankit 1 rishabh manish abhinav
echo ${arr[@]:1:4}    # ankit 1 rishabh manish 
echo ${arr[@]:2:3}    # 1 rishabh manish 
echo ${arr[0]:1:3}    # rak 
  
# Length of Particular element 
echo ${#arr[0]}        # 7 
echo ${#arr}        # 7 
  
# Size of an Array 
echo ${#arr[@]}        # 6 
echo ${#arr[*]}        # 6 
  
# Search in Array 
echo ${arr[@]/*[aA]*/}    # 1 
  
# Replacing Substring Temporary 
echo ${arr[@]//a/A}        # prAkhAr Ankit 1 rishAbh mAnish AbhinAv 
echo ${arr[@]}            # prakhar ankit 1 rishabh manish abhinav 
echo ${arr[0]//r/R}        # pRakhaR 
```
### pattern
Namibia
Nauru
Nepal
Netherlands
NewZealand
Nicaragua
Niger
Nigeria
NorthKorea
Norway
```shell=
declare -a arr
i=0
while read LINE
do
    arr[$i]=$LINE
    let i++
done
declare -a patter=(${arr[@]/*[aA]*/})
echo ${patter[@]}
```
Niger
### Ref
[1](https://go-linux.blogspot.com/2007/03/basharray.html)
[2](https://www.thegeekstuff.com/2010/06/bash-array-tutorial/)
## source
在現行 shell 環境中執行 shell
## exit
離開 bash 或結束 script

## unmask
預設檔案權限 0666
預設目錄權限 0777
```shell=
unmask 0022
檔案權限 0666 - 0022
目錄權限 0777 - 0022
```
## history
1. HISTFILE
root 執行過的指令都存在這
```shell=
# echo $HISTFILE
/root/.bash_history
```
2. HISTFILESIZE
指定歷史指令檔案的列數，若超過 HISTFILESIZE 大小排序在前的指令會被去除
3. HISTSIZE

## fc
列出登入主機後，最近執行的指令
```shell=
# fc -l
941      clear
942      ls
943      cd scapy/
944      ls
945      clear
946      dirs
947      : > a.txt
948      ls
949      cat a.txt
950      rm a.att
```

## set
設定 Bash shell 的屬性
```shell=
set -o # 顯示目前 bash shell 所有屬性狀態
set -o emacs # 打開 emacs 模式
set +o emacs # 關閉 emacs 模式
set -C Or set -o noclobber # 保護已存在檔案，避免轉向輸出被覆蓋
set -u # 變數不存在，則顯示錯誤訊息
set -e # 管線發生錯誤，立即結束 script
set -v # 顯示 script 讀入的每一列
set -f # 關閉萬用字源自動擴展
```
set -e：錯誤直接停止

set +e：錯誤不停止

set --：正常看到 - 後面是 option，現在不再是 option 而是一個命令參數。如-1 -2 …

set -a ：從這邊以後變數自動變成環境變數。

set -f ：不要解釋檔名的特殊字元例如wildcard *不再解釋為所有的意思了。

set -x ：debug shell scripts

set -o ignoreeof：一定要用exit離開shell,本來按Ctrl-D(eof)也可以

set -o noclobber：關掉I/O導向不準overwrite檔案

set -o notify：shell結束時報告background job的status

set -o noglob：關掉wildcard字元解釋 如 * ? [ ]

set +o：把-o的反向操作

set -：關掉 -v -x - 三種選項

set --：或者 set - 常常用在shell scripts裡面。

>set -o 是很常用的例如 set -o vi 設定 shell 的操作方式用vi方法，取回上個命令就是按 ESC 再按 k 囉。

## shopt
設定 Bash shell 行為模式
|指令|描述|
|---|---|
|shopt Or shopt -p| 顯示所有選項開關狀態|
|shopt -s option|啟用選項|
|shopt -u option|關閉選項|
|shopt -o option|和 set -o 相同選項來設定|
|shopt -q option|不顯示關閉狀態，以 0 表示啟用；1 表示關閉|

## exec
執行指定程式，取代原來的 shell；或使轉向動作成功。

```shell=
exec a.sh or exec ls # 執行 a.sh 或 ls ，取代目前 shell 。目前的 shell 環境隨即結束。
exec < datafile # 執行轉向輸入，凡是由標準輸入讀取資料的，皆改向 datafile 讀取。
```

## eval
讀取參數，透過 eval 結合成另一個指令，於以執行
```shell=
#! /bin/bash
l="ls -al"
eval $l # 讀去變數內容去讀取
```

## ulimit
調整使用者在 shell 環境中各種系統資源的使用上限。
```shell=
ulimit -Sn 1024
ulimit -Hn 4096
```
- -S
    - 不能超過上限，此範例最多為 1024
    - 在設定後還能更改
- -H
    - 不能超過最後底線，此範例最多為 4096
    - 在設定後不能在更改
    - 預設選項
    - 只能調低，不能調高

|參數|描述|
|---|---|
|-S|使用軟性的資源上限|
|-H|使用硬性的資源上限|
|-a|列出所有的資源限制的現況|
|-b|socket 緩衝區大小|
|-c|建立 core 檔案大小最大上限|
|-d|行程資料區段最大上限|
|-e|最大的排程能力上限(nice)|
|-f|shell 和其子 shell 可儲存檔案的最大上限|
|-i|暫停信號數量的最大上限|
|-l|行程可鎖住記憶體的最大上限|
|-m|常駔集大小的最大上限|
|-n|可開啟檔案數量的最大上限|
|-p|管線緩衝區的大小|
|-q|POSIX 信息佇列中最大位元的大小|
|-r|即時排程權力的最大上限|
|-s|堆疊大小的最大上限|
|-t|CPU 時間量的最大上限(秒計)|
|-u|使用者行程數量的最大上限|
|-v|虛擬記憶體的大小|
|-x|檔案鎖定數量的最大上限|

## ln
鏈結檔案
- 硬式
```shell=
$ sudo ln f1 f2 # f2 鏈結到 f1，f2 的檔案大小、內容和 f1 一樣
```
- 軟式
```shell=
$ sudo ln -s f1 f3 # 將 f3 鏈結到 f1，f3 是 f1 別名，取用 f3 時等於是存取 f1
$ sudo ln -sf f1 f3 # f 若 f3 存在則刪除，在重建一個 f3 的檔案
```
## find
在階層的目錄中找尋檔案
```shell=
$ sudo / -name '*.txt' # 從根目錄開始往下搜尋 .txt 的檔案
$ sudo / -name '*.txt' -fprint find.log # 結果存入 find.log 檔案中
$ sudo / -name '*.txt' -print > find.log # 結果存入 find.log 檔案中
$ sudo . -name '*.txt' -exec rm -f '{}' \; # {} 找到的檔案，； 是執行指令（exec）的終止符號，\ 跳脫字元用
$ sudo find /home/xxx/public_html -iname cc.php #不分大小寫，搜尋檔案名稱為 cc.php 的檔案
$ sudo find /etc -maxdepath 1 -type f -iname 'cc.php' # 指找 /etc 目錄裡的一般檔案（f）
```
## tar
打包
```shell=
$ sudo tar cvzf etc.tar.gz etc/ # c 建立 tar，v 顯示過程，z gzip 壓縮 tar 檔，f 檔名
```
檢查打包內容
```shell=
$ sudo tar tvzf etc.tar.gz # t 顯示打包內容
```
解包
```shell=
$ sudo tar xvzf etc.tar.gz # x 解包，z gzip 解壓縮
```
## sort
可依據不同的資料型態來排序
- -f
  - 忽略大小寫的差異，例如 A 與 a 視為編碼相同； 
- -b
  - 忽略最前面的空白字元部分； 
- -M
  - 以月份的名字來排序，例如 JAN, DEC 等排序方法；
- -n 
    - 使用『純數字』進行排序預設是以文字型態排序 ()；
- -r
  - 反向排序； 
- -u
  - ，相同的資料中僅出現一行； uniq 
- -t
  - 分隔符號，預設是 tab 鍵； 
- -k
  - 以第幾個區間 (field) 進行排序，

利用 last 將輸出的資料僅取帳號並加以排序
```shell=
$ last | cut -d ' ' -f 1 | sort | uniq

wtmp
```
## uniq
- -i
  - 忽略大小寫字元的不同； 
- -c
  - 進行計數 
- -u
    - 如果只想要輸出沒有重複的文字行，也就是說只要出現重複的文字行，就完全刪掉

```shell=
$ last | cut -d ' ' -f 1 | sort | uniq -c
    1
    39 reboot
    58 root
    1 wtmp
```
```shell=
A00
a00
01
01
00
00
02
02
A00
03
aa
aa
aa
$ uniq -u 
A00
a00
A00
03
```

## cut
- -d
  - 後面接分隔字元。與 -f 一起使用；
- -f
  - 依據 -d 的分隔字元將一段訊息分割成為數段，用 -f 取出第幾 段； 
- -c
  - 以字元 (characters) 的單位取出固定字元區間； 
 
![](https://i.imgur.com/d1iePeL.png)

![](https://i.imgur.com/mz9V0bT.png)

>-c，index 從 1 開始。
>cat -b 它也會把行數吃掉><


## tr
可以用來刪除一段訊息當中的文字，或者是進行文字訊息的替換。 
- -d
  - 刪除訊息當中的 SET1 這個字串； 
- -s
  - 取代重複的字元。 
- -c
  - 反向去執行
 
![](https://i.imgur.com/7IC4AHM.png)

![](https://i.imgur.com/zChN4ea.png)tr -d -c '0-9'

## paste
檔案列和列合併
- -d
    - 指定的間隔字符取代跳格字符
- -s
    - 將一個文件中的多行數據合併為一行進行顯示。
- \- 
    - 如果 file 部分寫成 - ，表示來自 standard input 的資料的意思。
```shell=
$ paste data1 data2 # data1 每一列和 data2 每一列合併，預設 TAB 分隔
$ paste -d'#' data1 data2 # # data1 每一列和 data2 每一列合併，用 # 分隔

```
## grep
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
- E
  - 擴展正規表達
  - ?、+、{}、|、()
- B
  - 打印匹配的行數並帶有後 N 行語句
- A
  - 打印匹配的行數並帶有前 N 行語句
- C
  - 打印匹配的行數並帶有前後 N 行語句
  
```shell=
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

找到，`grep` 狀態返回 `0`
沒找到，`grep` 狀態返回 `1`
找不到指定檔案，`grep` 狀態返回 `2`
## diff
比較兩個檔案之間差異

## comm
以列和列的方式，比較兩個已排序好的檔案
## xargs
由標準輸入，安排要執行的命令列和參數個數
[xargs](https://blog.gtwang.org/linux/xargs-command-examples-in-linux-unix/)

## seq
顯示序列數
```shell=
$ seq 10
1
2
3
4
5
6
7
8
9
10
```

```shell=
$ seq -w 15 # 等寬的數字
01
02
03
04
05
06
07
08
09
10
11
12
13
14
15
```
```shell=
$ seq -w 5 10 # 5 是起始；10 是終止
05
06
07
08
09
10
```
```shell=
$ seq -w 5 2 15 # 5 是起始；2 是遞增； 15 是終止
05
07
09
11
13
15
```
## getconf
查詢系統設定變數

```shell=
$ getconf -a # 顯示所有系統設定變數
LINK_MAX                           65000
_POSIX_LINK_MAX                    65000
MAX_CANON                          255
_POSIX_MAX_CANON                   255
MAX_INPUT                          255
_POSIX_MAX_INPUT                   255
NAME_MAX                           255
_POSIX_NAME_MAX                    255
PATH_MAX                           4096
_POSIX_PATH_MAX                    4096
PIPE_BUF                           4096
_POSIX_PIPE_BUF                    4096
SOCK_MAXBUF
_POSIX_ASYNC_IO
_POSIX_CHOWN_RESTRICTED            1
_POSIX_NO_TRUNC                    1
_POSIX_PRIO_IO
_POSIX_SYNC_IO
_POSIX_VDISABLE                    0
ARG_MAX                            2097152
ATEXIT_MAX                         2147483647
...
```

[IBM](https://www.ibm.com/support/knowledgecenter/zh/ssw_aix_71/com.ibm.aix.cmds2/getconf.htm)

## openssl
## hexdump & xxd
16 進制傾印

## lsof
找出主機 TCP/UDP 連線狀態
## curl
client 和 server 之間，使用不同協定傳輸資料

## grep
Grep 是一種多用途搜索工具，用於查找指定的字符串或正則表達式。存在各種選項，這使得可以以幾種不同的方式使用命令並處理許多不同的情況。例如，可以選擇不區分大小寫的搜索，或者僅顯示與指定搜索模式匹配的片段，或者僅顯示已找到指定字符串或正則表達式的輸入文件的行號。

- -E
    - 擴展的表達式
- -i
    - 不分大小寫
- -w
    - 強制PATTERN只匹配整個單詞
```shell=
grep -Eiw 'th(e|at|en|ose)'
```
[grep](https://www.thegeekstuff.com/2009/03/15-practical-unix-grep-command-examples/)
[regexpr](http://www.robelle.com/smugbook/regexpr.html)

## sed

## awk
awk 是一種編程語言，可以輕鬆處理結構化數據並生成格式化報告。
|參數|功能|
|---|---|
|-F|指定輸入檔案分隔符|
|-v|賦值一個用戶定義變量|

內至變數

|變數|說明|
|---|---|
|FILENAME|檔案名|
|NR|已讀的紀錄數|
|NF|瀏覽紀錄的域個數（切割後，列的個數）|

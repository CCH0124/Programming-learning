package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

func main() {
	file, err := os.Open("fileDemo/a.txt")
	if err != nil {
		fmt.Println("Open file err", err)
	}
	// 函數退出時，關閉檔案
	defer file.Close()

	reader := bufio.NewReader(file)
	for {
		str, err := reader.ReadString('\n') // 讀到換行就離開
		if err == io.EOF {                  // io.EOF 檔案末尾
			break
		}
		fmt.Print(str)
	}
	fmt.Println("file end")
}

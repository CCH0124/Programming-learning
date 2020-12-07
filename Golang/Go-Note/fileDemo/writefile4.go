package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

// 複寫已存在檔案
func main() {
	filePath := "fileDemo/a.txt"
	file, err := os.OpenFile(filePath, os.O_RDWR|os.O_APPEND, 066)
	if err != nil {
		fmt.Printf("Open file err=%v\n", err)
		return
	}
	defer file.Close()

	reader := bufio.NewReader(file)
	for {
		str, err := reader.ReadString('\n')
		if err == io.EOF {
			break
		}
		fmt.Println(str)
	}

	str := "C C C \n"
	writer := bufio.NewWriter(file) // buffer
	for i := 0; i < 3; i++ {
		writer.WriteString(str)
	}
	writer.Flush() // 寫入數據
}

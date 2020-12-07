package main

import "os"

import "fmt"

import "bufio"

// 建立檔案並寫入數據
func main() {
	filePath := "fileDemo/write.txt"
	file, err := os.OpenFile(filePath, os.O_WRONLY|os.O_CREATE, 0666)
	if err != nil {
		fmt.Printf("Open file err=%v\n", err)
		return
	}
	defer file.Close()

	str := "Hello world Go\n"
	writer := bufio.NewWriter(file) // buffer
	for i := 0; i < 5; i++ {
		writer.WriteString(str)
	}
	writer.Flush() // 寫入數據
}

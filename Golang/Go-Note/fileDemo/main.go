package main

import (
	"fmt"
	"os"
)

func main() {
	file, err := os.Open("fileDemo/a.txt")
	if err != nil {
		fmt.Println("Open file err", err)
	}
	fmt.Printf("file= %v", file)

	err = file.Close()
	if err != nil {
		fmt.Println("close file err ", err)
	}
}

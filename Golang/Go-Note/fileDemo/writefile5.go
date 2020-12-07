package main

import (
	"fmt"
	"io/ioutil"
)

func main() {
	file1Path := "fileDemo/a.txt"
	file2Path := "fileDemo/b.txt"

	data, err := ioutil.ReadFile(file1Path)

	if err != nil {
		fmt.Println("read file err: ", err)
		return
	}
	err = ioutil.WriteFile(file2Path, data, 0666)
	if err != nil {
		fmt.Println("write file err: ", err)
		return
	}
}

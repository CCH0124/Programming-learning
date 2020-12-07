package main

import (
	"fmt"
	"io/ioutil"
)

func main() {
	file := "fileDemo/a.txt"
	content, err := ioutil.ReadFile(file)
	if err != nil {
		fmt.Println("Reader file err", err)
	}

	fmt.Printf("%v", string(content))
}

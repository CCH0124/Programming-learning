package main

import (
	"fmt"
	"strconv"
	"time"
)

func test() {
	for i := 1; i <= 10; i++ {
		fmt.Println("test Hello world " + strconv.Itoa(i))
		time.Sleep(time.Second)
	}
}
func main() {
	test()
	for i := 1; i <= 10; i++ {
		fmt.Println("main Hello world " + strconv.Itoa(i))
		time.Sleep(time.Second)
	}
}

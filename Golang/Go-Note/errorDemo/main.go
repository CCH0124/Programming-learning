package main

import (
	"fmt"
)

func test() {
	defer func() {
		err := recover()
		if err != nil {
			fmt.Println(err)
		}
	}()
	num1 := 10
	num2 := 0
	res := num1 + num2
	fmt.Println("result=", res)
}
func main() {

}

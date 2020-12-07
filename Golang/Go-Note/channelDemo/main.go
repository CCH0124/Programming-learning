package main

import "fmt"

func main() {
	var intChan chan int
	intChan = make(chan int, 3)
	fmt.Printf("%v, %v\n", intChan, &intChan)

	/// write data
	intChan <- 10
	num := 22
	intChan <- num

	/// check channel
	fmt.Println("chan len: ", len(intChan))
	fmt.Println("chan cap: ", cap(intChan))

	/// read data
	var num2 int
	num2 = <-intChan
	fmt.Println("num2: ", num2)
	fmt.Println("chan len: ", len(intChan))
	fmt.Println("chan cap: ", cap(intChan))
}

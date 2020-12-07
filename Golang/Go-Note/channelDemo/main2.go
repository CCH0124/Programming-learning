package main

import "fmt"

func main() {
	intChan := make(chan int, 3)
	intChan <- 100
	intChan <- 200
	close(intChan)
	/// can read
	a := <-intChan
	fmt.Println(a)

	intChan2 := make(chan int, 100)
	for i := 0; i < 100; i++ {
		intChan2 <- i * 3
	}
	close(intChan)
	for v := range intChan2 {
		fmt.Println("v= ", v)
	}
}

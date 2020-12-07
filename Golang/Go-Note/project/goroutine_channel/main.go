package main

import (
	"fmt"
	"time"
)

func writeData(intChan chan int) {
	for i := 0; i <= 20; i++ {
		intChan <- i
		fmt.Println("Write ", i)
		time.Sleep(time.Second)
	}
	close(intChan)
}

func readData(intChen chan int, exitChan chan bool) {
	for {
		v, ok := <-intChen
		if !ok {
			break
		}
		time.Sleep(time.Second)
		fmt.Printf("Read %v\n", v)
	}

	exitChan <- true
	close(exitChan)
}
func main() {
	intChan := make(chan int, 50)
	exitChan := make(chan bool, 1)

	go writeData(intChan)
	go readData(intChan, exitChan)
	for {
		_, ok := <-exitChan
		if !ok {
			break
		}
	}
}

package main

import (
	"fmt"
)

func main() {
	var arr [5]int = [...]int{1, 22, 33, 44, 55}
	slice := arr[1:3]
	fmt.Println("Arr:", arr)
	fmt.Println("slice 元素:", slice)
	fmt.Println("slice len:", len(slice))
	fmt.Println("slice cap:", cap(slice)) // 容量可任意變動

	fmt.Printf("Arr addr: %p\n", &arr)
	fmt.Printf("Arr[1] addr: %p\n", &arr[1])
	fmt.Printf("slice addr: %p\n", &slice)
	fmt.Printf("slice[0] addr: %p slice[0]==%v", &slice[0], slice[0])
}

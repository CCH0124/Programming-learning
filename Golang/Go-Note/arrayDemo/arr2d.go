package main

import "fmt"

func main() {
	var arr [2][3]int
	arr[1][1] = 10
	fmt.Println(arr)

	fmt.Printf("arr[0] add %p \n", &arr[0])
	fmt.Printf("arr[1] add %p \n", &arr[1])

	fmt.Printf("arr[0][0] add %p \n", &arr[0][0])
	fmt.Printf("arr[1][0] add %p \n", &arr[1][0])
}

package main

import "fmt"

func main() {
	// var a []int
	// a = append(a, 1)
	// for i := range a {
	// 	fmt.Printf("a[%d]: %d\n", i, a[i])
	// }
	// fmt.Println("========================================")
	// a = append(a, 1, 2, 3)
	// for i := range a {
	// 	fmt.Printf("a[%d]: %d\n", i, a[i])
	// }
	// fmt.Println("========================================")
	// a = append(a, []int{1, 2, 3}...)
	// for i := range a {
	// 	fmt.Printf("a[%d]: %d\n", i, a[i])
	// }
	var a = []int{1, 2, 3}
	a = append([]int{0}, a...)
	for i := range a {
		fmt.Printf("a[%d]: %d\n", i, a[i])
	}
	fmt.Println("========================================")
	a = append([]int{-3, -2, -1}, a...)
	for i := range a {
		fmt.Printf("a[%d]: %d\n", i, a[i])
	}
}

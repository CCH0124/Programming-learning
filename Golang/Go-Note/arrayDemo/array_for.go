package main

import (
	"fmt"
)

func main() {
	var a = [...]int{1, 2, 3}
	var b = &a // Array 過大時，會造成賦值或傳遞時複製的開銷，可以使用 point 來避免
	var c = [...]int{2: 3, 1: 2}
	/**
	* Array include len and cap
	* len 計算長度
	* cap 容量
	 */
	for i := range a {
		fmt.Printf("a[%d]: %d\n", i, a[i])
	}

	for i, v := range b {
		fmt.Printf("b[%d]: %d\n", i, v)
	}

	for i := 0; i < len(c); i++ {
		fmt.Printf("c[%d]: %d\n", i, c[i])
	}

	/*
	* 保證不越界
	 */
	var times [5][0]int
	for range times {
		fmt.Println("hello")
	}
}

package main

import (
	"fmt"
)

func main() {
	var a = [...]int{1, 2, 3}
	var b = &a // Array 過大時，會造成賦值或傳遞時複製的開銷，可以使用 point 來避免

	fmt.Println(a[0], a[1])
	fmt.Println(b[0], b[1])

	for i, v := range b {
		fmt.Println(i, v)
	}
}

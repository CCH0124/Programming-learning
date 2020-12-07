package main

import (
	"fmt"
)

func main() {
	/*
	* [] 表示 slice
	* [...] 表示 array
	 */
	var a [3]int
	var b = [...]int{1, 2, 3}
	var c = [...]int{2: 3, 1: 2} // 2 與 1 式索引，後面帶該所引的值
	var d = [...]int{1, 2, 4: 5, 6}

	fmt.Println(a)
	fmt.Println(b)
	fmt.Println(c)
	fmt.Println(d)

}

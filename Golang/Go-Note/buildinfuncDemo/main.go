package main

import (
	"fmt"
)

func main() {
	num1 := 100
	fmt.Printf("Type %T Value %v Address %v\n", num1, num1, &num1) // Type int Value 100 Address 0xc0000120c8
	num2 := new(int)
	fmt.Printf("Type %T Value %v Address %v 指向的值 %v", num2, num2, &num2, *num2) // Type *int Value 0xc0000120f8 Address 0xc000006030 指向的值 0
}

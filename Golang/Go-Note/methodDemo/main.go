package main

import (
	"fmt"
)

type Person struct {
	Name string
}

func (p Person) speak() {
	fmt.Println(p.Name, "nice")
}

func (p Person) sum() {
	res := 0
	for i := 1; i <= 1000; i++ {
		res += i
	}
	fmt.Println(p.Name, "sum=", res)
}

func (p Person) sum1(n int) {
	res := 0
	for i := 1; i <= n; i++ {
		res += i
	}
	fmt.Println(p.Name, "sum1=", res)
}

func (p Person) getSum(n int, m int) int {
	return n + m
}

func (p Person) test() {
	p.Name = "Jack"
	fmt.Println(p.Name)
}
func main() {
	var p Person
	p.Name = "Tom"
	p.test()
	fmt.Println(p.Name)

	p.speak()
	p.sum()
	p.sum1(10)
	fmt.Println(p.getSum(10, 20))
}

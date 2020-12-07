package main

import "fmt"

type B interface {
	testB()
}
type C interface {
	testC()
}
type A interface {
	B
	C
	testA()
}

type Stu struct {
}

func (stu Stu) testA() {
	fmt.Println("A")
}
func (stu Stu) testB() {
	fmt.Println("B")
}
func (stu Stu) testC() {
	fmt.Println("C")
}
func main() {
	var stu Stu
	var a A = stu
	a.testA()

	var b B = stu
	b.testB()
}

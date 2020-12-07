package main

import "fmt"

type A interface {
	Say()
}

type B interface {
	Hello()
}
type Stu struct {
	Name string
}

func (stu Stu) Say() {
	fmt.Println("Stu...")
}

// 自定義類型
type integer int

func (i integer) Say() {
	fmt.Println("integer....", i)
}

type Monster struct {
}

func (m Monster) Say() {
	fmt.Println("Monster Say...")
}
func (m Monster) Hello() {
	fmt.Println("Monster Hello...")
}
func main() {
	var s Stu
	var a A = s
	a.Say()

	var i integer = 10
	var b A = i
	b.Say()

	var m Monster
	var a2 A = m
	var b2 B = m
	a2.Say()
	b2.Hello()
}

package main

import (
	"../model"
	"fmt"
)

func main() {
	p := model.NewPerson("kevin")
	p.SetAge(24)
	p.SetSal(10000)
	fmt.Println(p)
	fmt.Println(p.Name, "Age: ", p.GetAge(), " Sal: ", p.GetSal())
}

package main

import "fmt"

type Cat struct {
	Name  string
	Age   int
	Color string
}

func main() {
	var cat1 Cat
	cat1.Name = "Itachi"
	cat1.Age = 5
	cat1.Color = "black"
	fmt.Println(cat1.Color)
}

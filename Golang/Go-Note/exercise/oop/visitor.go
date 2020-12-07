package main

import "fmt"

type Visitor struct {
	Name string
	Age  int
}

func (visitor *Visitor) showPrice() {
	if visitor.Age >= 90 || visitor.Age <= 8 {
		fmt.Println("Don't fit")
	} else if visitor.Age > 18 {
		fmt.Printf("name: %v age: %v 收費 20 \n", visitor.Name, visitor.Age)
	} else {
		fmt.Printf("name: %v age: %v free \n", visitor.Name, visitor.Age)
	}
}

func main() {
	var v Visitor
	for {
		fmt.Println("input name: ")
		fmt.Scanln(&v.Name)
		if v.Name == "exit" {
			fmt.Println("exit system")
			break
		}
		fmt.Println("input age: ")
		fmt.Scanln(&v.Age)
		v.showPrice()
	}
}

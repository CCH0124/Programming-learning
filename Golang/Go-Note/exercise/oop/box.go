package main

import "fmt"

type Box struct {
	l      float64
	width  float64
	height float64
}

func (box *Box) getVolumn() float64 {
	return box.l * box.width * box.height
}
func main() {
	var box Box
	box.l = 2.2
	box.width = 10
	box.height = 20

	fmt.Println((&box).getVolumn())
}

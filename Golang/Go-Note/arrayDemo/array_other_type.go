package main

import (
	"fmt"
)

func main() {
	var s1 = [2]string{"hello", "world"}
	var s2 = [...]string{"你好", "世界"}
	var s3 = [...]string{1: "世界", 0: "你好"}
	fmt.Println(s1[1])
	fmt.Println(s2[0])
	fmt.Println(s3[0])

	// var line1 [2]image.Point
	// var line2 = [...]image.Point{image.Point{X: 0, Y: 0}, image.Point{X: 1, Y: 1}}
	// var line3 = [...]image.Point{{0, 0}, {1, 1}}

	// var decoder1 [2]func(io.Reader) (image.Image, error)
	// var decoder2 = [...]func(io.Reader) (image.Image, error){
	// 	png.Decode,
	// 	jpeg.Decode,
	// }

	// var unknown1 [2]interface{}
	// var unknown2 = [...]interface{}{123, "你好"}

	// var chanList = [2]chan int{}

	/*
	* 長度為 0 的 array
	 */
	var d [0]int
	var e = [0]int{}
	var f = [...]int{}
}

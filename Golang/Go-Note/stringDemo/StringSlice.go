package main

import (
	"fmt"
	"reflect"
	"unsafe"
)

func main() {
	s := "hello, world"
	hello := s[:5]
	world := s[7:]

	s1 := "hello, world"[:5]
	s2 := "hello, world"[7:]

	fmt.Println("len(s):", (*reflect.StringHeader)(unsafe.Pointer(&s)).Len)   // 12
	fmt.Println("len(s1):", (*reflect.StringHeader)(unsafe.Pointer(&s1)).Len) // 5
	fmt.Println("len(hello):", (*reflect.StringHeader)(unsafe.Pointer(&hello)).Len)
	fmt.Println("len(s2):", (*reflect.StringHeader)(unsafe.Pointer(&s2)).Len) // 5
	fmt.Println("len(world):", (*reflect.StringHeader)(unsafe.Pointer(&world)).Len)

	fmt.Printf("%p\n", &s1)
	fmt.Printf("%p\n", &hello)
}

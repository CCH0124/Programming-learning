package main

import "fmt"

func main() {
	// 世界 UTF8
	fmt.Printf("%#v\n", []byte("Hello, 世界"))
	// []byte{0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c, 0x20, 0xe4, 0xb8, 0x96, 0xe7, 0x95, 0x8c}
	fmt.Println("\xe4\xb8\x96") //世
	fmt.Println("\xe7\x95\x8c") // 界
	fmt.Println()
	for i, c := range []byte("世界abc") {
		fmt.Println(i, c)
	}

	for i, c := range "\xe4\x00\x00\xe7\x95\x8cabc" {
		fmt.Println(i, c)
	}
}

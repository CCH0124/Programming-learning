package main

import (
	"fmt"

	tree "data.struct/tree"
)

func main() {
	root := tree.New(5, 3)
	fmt.Println("==================前序===================")
	root.PreOrder(root)
	fmt.Println()
	fmt.Println("==================中序===================")
	root.InfixOrder(root)
	fmt.Println()
	fmt.Println("==================後序===================")
	root.PostOrder(root)
	fmt.Println()
	fmt.Println("==================Tree Height===================")
	fmt.Println(root.TreeHeight(root))

}

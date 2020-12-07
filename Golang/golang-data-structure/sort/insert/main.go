package main

import "fmt"

func sort(arr []int) {
	for i := 1; i < len(arr); i++ {
		insertVal := arr[i]
		insertIndex := i - 1
		for insertIndex >= 0 && insertVal < arr[insertIndex] {
			arr[insertIndex+1] = arr[insertIndex]
			insertIndex--
		}
		arr[insertIndex+1] = insertVal

		fmt.Println("第 ", i, " 輪")
		for _, v := range arr {
			fmt.Printf("%-2d", v)
		}
		fmt.Println()
	}
}

func main() {
	arr := []int{3, 6, 2, 8, 1}
	sort(arr)
}

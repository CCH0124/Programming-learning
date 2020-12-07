package main

import "fmt"

func sort(arr []int) {
	for i := 0; i < len(arr)-1; i++ {
		minIndex := i
		min := arr[i]
		for j := i; j < len(arr); j++ {
			if min > arr[j] {
				min = arr[j]
				minIndex = j
			}
		}
		if minIndex != i {
			arr[minIndex] = arr[i]
			arr[i] = min
		}

		fmt.Println("第", i+1, "輪")
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

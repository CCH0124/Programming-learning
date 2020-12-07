package main

import "fmt"

func sort(arr []int) {
	temp := 0
	for i := 0; i < len(arr); i++ {
		for j := 0; j < len(arr)-1; j++ {
			if arr[j] > arr[j+1] {
				temp = arr[j]
				arr[j] = arr[j+1]
				arr[j+1] = temp
			}
		}
	}
	for _, v := range arr {
		fmt.Printf("%-2d", v)
	}
}

func main() {
	arr := []int{6, 2, 10, 4, 3}
	sort(arr)
}

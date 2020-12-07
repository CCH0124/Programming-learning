package main

import (
	"fmt"
	"unsafe"
)

func main() {
	var nums [4]int
	nums[0] = 1
	nums[1] = 2
	nums[2] = 3
	nums[3] = 4

	var strings = [3]string{
		"Itachi",
		"Naruto",
		"Madara",
	}

	two_dim := [][]float64{
		[]float64{1.0, 2.0, 3.0},
		[]float64{4, 5.0, 6},
	}

	fmt.Println("nums array size:", unsafe.Sizeof(nums))
	fmt.Println("string array size:", unsafe.Sizeof(strings))
	fmt.Println("float array size:", unsafe.Sizeof(two_dim))
	fmt.Print("----------------------------------------------------------------")
	fmt.Printf("%p\n", &nums)
	for i, _ := range nums {
		// fmt.Print(&v, "\t")
		fmt.Printf("%p\n", &nums[i])
	}

	fmt.Println("--------------------------------------------------")

	fmt.Printf("%p\n", &strings)
	for i := 0; i < len(strings); i++ {
		fmt.Println(&strings[i])
	}

	fmt.Println("--------------------------------------------------")
	fmt.Printf("%p\n", &two_dim)
	fmt.Printf("%p\n", &two_dim[0])
	fmt.Printf("%p\n", &two_dim[1])
	for i, one := range two_dim {
		for j, _ := range one {
			fmt.Printf("%p\t", &two_dim[i][j])
		}
		fmt.Println()
	}

}

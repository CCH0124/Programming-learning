package scan

import (
	"fmt"
)

func main() {
	var name string
	var age int
	var sal float32
	var isPass bool
	// fmt.Println("Please! Input Name")
	// fmt.Scanln(&name)

	// fmt.Println("Please! Input Age")
	// fmt.Scanln(&age)

	// fmt.Println("Please! Input Salary")
	// fmt.Scanln(&sal)

	// fmt.Println("Please! Input is pass")
	// fmt.Scanln(&isPass)

	// fmt.Printf("Name %v \n Age %v \n Salary %v \n is pass %v", name, age, sal, isPass)

	fmt.Println("Input name, age, salary, is pass")
	fmt.Scanf("%s %d %f %t", &name, &age, &sal, &isPass)
	fmt.Printf("Name %v \n Age %v \n Salary %v \n is pass %v", name, age, sal, isPass)
}

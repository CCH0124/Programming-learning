package main

import (
	"fmt"
)

type Student struct {
	name   string
	gender string
	age    int
	id     int
	score  float64
}

func (student *Student) say() string {
	infoStr := fmt.Sprintf("student info : name=[%v] gender=[%v] age=[%v] id=[%v] score=[%v]",
		student.name, student.gender, student.age, student.id, student.score)

	return infoStr
}
func main() {
	var stu1 = Student{
		name:   "tom",
		gender: "male",
		age:    18,
		id:     1000,
		score:  92.6,
	}
	fmt.Println((&stu1).say())

}

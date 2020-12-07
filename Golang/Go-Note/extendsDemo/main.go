package main

import (
	"fmt"
)

type Student struct {
	Name  string
	Age   int
	Score float64
}

func (stu *Student) ShowInfo() {
	fmt.Println("name: ", stu.Name, "age: ", stu.Age, "score: ", stu.Score)
}

func (stu *Student) SetScore(score float64) {
	stu.Score = score
}

type Pupil struct {
	Student
}

func (p *Pupil) test() {
	fmt.Println("testing....")
}

type Graduate struct {
	Student
}

func (g *Graduate) test() {
	fmt.Println("testing....")
}

func main() {
	pupil := &Pupil{}
	pupil.Student.Name = "kevin"
	pupil.Student.Age = 8
	pupil.test()
	pupil.Student.SetScore(64)
	pupil.Student.ShowInfo()

	graduate := &Graduate{}
	graduate.Student.Name = "mary"
	graduate.Student.Age = 28
	graduate.test()
	graduate.Student.SetScore(100)
	graduate.Student.ShowInfo()
}

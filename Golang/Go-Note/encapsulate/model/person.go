package model

import "errors"

type person struct {
	Name string
	age  int
	sal  float64
}

// 工廠模式函數，相當於建構方法
func NewPerson(name string) *person {
	return &person{
		Name: name,
	}
}

func (p *person) SetAge(age int) error {
	if age > 0 && age < 150 {
		p.age = age
	} else {
		return errors.New("input age error")
	}
	return nil
}

func (p *person) GetAge() int {
	return p.age
}

func (p *person) SetSal(sal float64) error {
	if sal > 0 && sal <= 50000 {
		p.sal = sal
	} else {
		return errors.New("input sal error")
	}
	return nil
}

func (p *person) GetSal() float64 {
	return p.sal
}

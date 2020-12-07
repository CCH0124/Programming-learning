package main

import (
	"fmt"
)

type Stack struct {
	stack []int
}

func NewStack() *Stack {
	return &Stack{}
}

func (s *Stack) isEmpty() bool {
	return len(s.stack) == 0
}

func (s *Stack) size() int {
	return len(s.stack)
}

func (s *Stack) push(value int) {
	s.stack = append(s.stack, value)
}

func (s *Stack) pop() int {
	if s.isEmpty() {
		panic("Stack is Empty")
	}
	size := s.size() - 1
	element := s.stack[size]
	s.stack = s.stack[:size]
	return element

}

func (s *Stack) print() {
	stackLen := s.size() - 1
	for stackLen >= 0 {
		fmt.Println(s.stack[stackLen])
		stackLen = stackLen - 1
	}
}

func main() {
	// stack := NewStack()
	// fmt.Println(stack.Top)
	stack := NewStack()
	stack.push(1)
	stack.push(2)
	stack.push(3)
	stack.push(4)
	stack.print()
	stack.size()
	fmt.Println("===============POP================")
	fmt.Println(stack.pop())
	fmt.Println("===============================")
	stack.print()
	stack.push(3)
	stack.push(10)
	stack.size()
	fmt.Println("===============POP================")
	fmt.Println(stack.pop())
	fmt.Println("===============================")
	stack.print()
}

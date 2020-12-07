package main

import "fmt"

type Queue struct {
	queue []int
}

func NewQueue() *Queue {
	return &Queue{}
}

func (q *Queue) isEmpty() bool {
	return q.size() == 0
}

func (q *Queue) size() int {
	return len(q.queue)
}

func (q *Queue) push(val int) {
	q.queue = append(q.queue, val)
}

func (q *Queue) pop() int {
	if q.isEmpty() {
		panic("Queue is Empty")
	}
	size := q.size()
	element := q.queue[0]
	if size != 0 {
		q.queue = q.queue[1:size]
	} else {
		q.queue = q.queue[:size]
	}

	return element
}

func (q *Queue) print() {
	if q.isEmpty() {
		panic("Queue is Empty")
	}
	for _, v := range q.queue {
		fmt.Println(v)
	}
}

func main() {
	queue := NewQueue()
	fmt.Println(queue.isEmpty())
	queue.push(1)
	queue.push(2)
	queue.push(3)
	fmt.Println("size: ", queue.size())
	fmt.Println(queue.isEmpty())
	queue.print()
	fmt.Println("=============Pop================")
	fmt.Println(queue.pop())
	fmt.Println(queue.pop())
	fmt.Println("=============Push================")
	queue.push(4)
	queue.push(5)
	fmt.Println("size: ", queue.size())
	queue.print()
}

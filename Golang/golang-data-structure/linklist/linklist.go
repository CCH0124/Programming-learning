package main

import (
	"errors"
	"fmt"
)

type Node struct {
	data int
	head *Node
	next *Node
}

func (n *Node) Init() *Node {
	return &Node{}
}

func (n *Node) add(node *Node) error {
	if n.head == nil {
		n.head = node
	} else {
		currentNode := n.head
		for currentNode.next != nil {
			currentNode = currentNode.next
		}
		currentNode.next = node
	}
	return nil
}

func (n *Node) addFront(node *Node) error {
	if n.head == nil {
		n.head = node
	} else {
		node.next = n.head
		n.head = node
	}
	return nil
}

func (n *Node) remove(val int) {
	if n.head == nil {
		n.exception()
	}
	if n.head.data == val {
		n.head = n.head.next
		return
	}
	current := n.head
	for current.next != nil && current.next.data != val {
		current = current.next
	}

	if current.next != nil {
		current.next = current.next.next
	}
}

func (n *Node) get(index int) int {
	size := n.size()
	if n.head == nil || index <= 0 || index > size {
		n.indexException()
	}
	current := n.head
	position := 0
	for position < index-1 {
		current = current.next
		position++
	}
	return current.data
}

func (n *Node) exception() error {
	return errors.New("Null head")
}

func (n *Node) indexException() error {
	return errors.New("Error Index Value")
}

func (n *Node) size() int {
	length := 0
	current := n.head
	if current == nil {
		return length
	}
	for current != nil {
		length = length + 1
		current = current.next
	}
	return length
}

func (n *Node) print() {
	current := n.head
	for current != nil {
		fmt.Println(current.data)
		current = current.next
	}
}

func main() {
	singList := new(Node)
	var node *Node = new(Node)
	(*node).data = 10

	var node2 *Node = new(Node)
	(*node2).data = 20

	var node3 *Node = new(Node)
	(*node3).data = 30

	singList.add(node)
	singList.add(node2)
	singList.add(node3)

	singList.print()

	var node4 *Node = new(Node)
	(*node4).data = 40
	var node5 *Node = new(Node)
	(*node5).data = 50

	fmt.Println("---------------addFront-------------------")
	singList.addFront(node4)
	singList.addFront(node5)

	singList.print()
	fmt.Println("---------------Remove-------------------")
	singList.remove(50)
	singList.remove(30)

	singList.print()
	fmt.Println("---------------Get-------------------")
	var node6 *Node = new(Node)
	(*node6).data = 100
	var node7 *Node = new(Node)
	(*node7).data = 300
	singList.add(node6)
	singList.add(node7)
	singList.print()
	fmt.Println("---------------Get1-------------------")
	fmt.Println(singList.get(1))
	fmt.Println("---------------Get4-------------------")
	fmt.Println(singList.get(4))
	fmt.Println("---------------Get3-------------------")
	fmt.Println(singList.get(3))

}

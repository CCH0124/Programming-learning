package tree

import (
	"fmt"
	"math/rand"
)

type Tree struct {
	Val   int
	Left  *Tree
	Right *Tree
}

func NewTree() *Tree {
	return &Tree{}
}

func New(n, k int) *Tree {
	var t *Tree
	for _, v := range rand.Perm(n) {
		t = t.insert(t, (1+v)*k)
	}
	return t
}

func (t *Tree) insert(binaryTree *Tree, val int) *Tree {
	if binaryTree == nil {
		binaryTree = &Tree{val, nil, nil}
	} else if val < binaryTree.Val {
		binaryTree.Left = binaryTree.insert(binaryTree.Left, val)
	} else {
		binaryTree.Right = binaryTree.insert(binaryTree.Right, val)
	}

	return binaryTree
}

func (t *Tree) PreOrder(node *Tree) {
	if node != nil {
		fmt.Printf("%-4d", node.Val)
		t.PreOrder(node.Left)
		t.PreOrder(node.Right)
	}
}

func (t *Tree) InfixOrder(node *Tree) {
	if node != nil {
		t.InfixOrder(node.Left)
		fmt.Printf("%-4d", node.Val)
		t.InfixOrder(node.Right)
	}
}

func (t *Tree) PostOrder(node *Tree) {
	if node != nil {
		t.PostOrder(node.Left)
		t.PostOrder(node.Right)
		fmt.Printf("%-4d", node.Val)
	}
}

func (t *Tree) TreeHeight(root *Tree) int {
	if root == nil {
		return 0
	}
	leftDepth := t.TreeHeight(root.Left)
	rightDepth := t.TreeHeight(root.Right)
	if leftDepth > rightDepth {
		return leftDepth + 1
	}
	return rightDepth + 1
}

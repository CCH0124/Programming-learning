package main

import "fmt"

type Usb interface {
	Start() // Method
	Stop()  // Method
}

// implement Usb Method
type Phone struct {
}

// implement Usb Method
func (p Phone) Start() {
	fmt.Println("working...")
}
func (p Phone) Stop() {
	fmt.Println("Stopping...")
}

type Camera struct {
}

// implement Usb Method
func (c Camera) Start() {
	fmt.Println("Camera working...")
}
func (c Camera) Stop() {
	fmt.Println("Camera Stopping...")
}

type Computer struct {
}

// 實現 Usb interface 所有方法
func (computer *Computer) Working(usb Usb) {
	usb.Start()
	usb.Stop()
}
func main() {
	c := Computer{}
	p := Phone{}
	ca := Camera{}
	c.Working(p)
	c.Working(ca)
}

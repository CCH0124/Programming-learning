package main

import (
	"encoding/json"
	"fmt"
)

type Monster struct {
	Name     string
	Age      int
	Birthady string
	Sal      float64
	Skill    string
}

func testMonster() {
	monster := Monster{
		Name:     "Itachi",
		Age:      200,
		Birthady: "2010-02-01",
		Sal:      6000.0,
		Skill:    "天照",
	}

	data, err := json.Marshal(&monster)
	if err != nil {
		fmt.Println("serial error: ", err)
	}
	fmt.Println("serail result: ", string(data))
}

// map serial
func testMap() {
	var a map[string]interface{} // interface{} any type
	a = make(map[string]interface{})
	a["name"] = "Naruto"
	a["age"] = 1000
	a["address"] = "木業"

	data, err := json.Marshal(&a)
	if err != nil {
		fmt.Println("serial error: ", err)
	}
	fmt.Println("serail result: ", string(data))
}

// slice serial
func testSlice() {
	var slice []map[string]interface{}
	var m1 map[string]interface{}

	m1 = make(map[string]interface{})
	m1["name"] = "jack"
	m1["sal"] = 43.6
	m1["add"] = "高雄"
	slice = append(slice, m1)

	var m2 map[string]interface{}
	m2 = make(map[string]interface{})
	m2["name"] = "jerry"
	m2["sal"] = 190.2
	m2["add"] = "台北"
	slice = append(slice, m2)

	data, err := json.Marshal(&slice)
	if err != nil {
		fmt.Println("serial error: ", err)
	}
	fmt.Println("serail result: ", string(data))
}
func main() {
	testMonster()
	testMap()
	testSlice()
}

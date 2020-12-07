package main

import "encoding/json"
import "fmt"

type Monster struct {
	Name     string
	Age      int
	Birthady string
	Sal      float64
	Skill    string
}

func unserial() {
	str := "{\"Name\":\"Itachi\",\"Age\":200,\"Birthady\":\"2010-02-01\",\"Sal\":6000,\"Skill\":\"天照\"}"

	var monster Monster

	err := json.Unmarshal([]byte(str), &monster)
	if err != nil {
		fmt.Println("unmardhal err", err)
	}
	fmt.Println("反序列化後", monster)
}

func unserialMap() {
	str := "{\"address\":\"木業\",\"age\":1000,\"name\":\"Naruto\"}"
	var a map[string]interface{} 
	// 反序列化無須 make，Unmarshal 會進行 make 動作
	err := json.Unmarshal([]byte(str), &a)
	if err != nil {
		fmt.Println("unmardhal err", err)
	}
	fmt.Println("反序列化後", a)
}

func unserialSlice() {
	str := "[{\"add\":\"高雄\",\"name\":\"jack\",\"sal\":43.6},{\"add\":\"台北\",\"name\":\"jerry\",\"sal\":190.2}]"
	var slice []map[string]interface{}
	err := json.Unmarshal([]byte(str), &slice)
	if err != nil {
		fmt.Println("unmardhal err", err)
	}
	fmt.Println("反序列化後", slice)
}
func main() {
	unserial()
	unserialMap()
	unserialSlice()
}

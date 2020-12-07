package main

import (
	"fmt"
	"math/rand"
	"sort"
)

type Hero struct {
	Name string
	Age  int
}
type HeroSlic []Hero

// Len、Less、Swap 實現 interface
func (h HeroSlic) Len() int {
	return len(h)
}

func (h HeroSlic) Less(i, j int) bool {
	return h[i].Age < h[j].Age
}

func (h HeroSlic) Swap(i, j int) {
	temp := h[i]
	h[i] = h[j]
	h[j] = temp
}

func main() {
	var heros HeroSlic
	for i := 0; i < 10; i++ {
		hero := Hero{
			Name: fmt.Sprintf("Hero~%d", rand.Intn(100)),
			Age:  rand.Intn(100),
		}
		heros = append(heros, hero)
	}

	// 排序前
	for _, v := range heros {
		fmt.Println(v)
	}
	fmt.Println("----------------------------------------------------------")
	sort.Sort(heros)

	for _, v := range heros {
		fmt.Println(v)
	}
}

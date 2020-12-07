package main

import (
	"fmt"
	"time"
)

func main() {
	// fmt.Printf("now=%v now type=%T", time.Now(), time.Now())
	now := time.Now()
	// fmt.Printf("Year=%v\n Month=%v\n Day=%v\n Hour=%v\n Minute=%v\n Sec=%v\n", now.Year(), now.Month(), now.Day(), now.Hour(), now.Minute(), now.Second())
	// fmt.Printf("%d-%d-%d\n %d:%d:%d\n", now.Year(), now.Month(),
	// 	now.Day(), now.Hour(), now.Minute(), now.Second())
	// dateStr := fmt.Sprintf("%d-%d-%d %d:%d:%d\n", now.Year(), now.Month(),
	// 	now.Day(), now.Hour(), now.Minute(), now.Second())
	// fmt.Printf("dateStr=%v", dateStr)
	fmt.Printf((now.Format("2006/01/02 15:04:05")))
}

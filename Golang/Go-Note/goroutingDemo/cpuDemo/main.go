package main

import (
	"fmt"
	"runtime"
)

func main() {
	cpuNum := runtime.NumCPU()
	fmt.Println("CPU Num: ", cpuNum)

	// runtime.GOMAXPROCS() 設置 CPU 數量
}

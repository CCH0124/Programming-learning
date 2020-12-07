package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

func CopyFile(dstFileName string, srcFileName string) (written int64, err error) {
	srcfile, err := os.Open(srcFileName)
	if err != nil {
		fmt.Println("Open file err: ", err)
	}

	reader := bufio.NewReader(srcfile)

	dstfile, err := os.OpenFile(dstFileName, os.O_WRONLY|os.O_CREATE, 0666)
	if err != nil {
		fmt.Printf("Open file err=%v\n", err)
		return
	}

	write := bufio.NewWriter(dstfile)

	defer dstfile.Close()
	defer srcfile.Close()

	return io.Copy(write, reader)
}
func main() {
	srcfile := "fileDemo/telnet.png"
	dstfile := "fileDemo/telnet_copy.png"

	_, err := CopyFile(dstfile, srcfile)
	if err == nil {
		fmt.Println("Copy finished!")
	} else {
		fmt.Println("Copy fail: ", err)
	}
}

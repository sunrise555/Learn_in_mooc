package main

import (
	"fmt"
	// "time"
)

/**
并发demo
 */
func main() {
	// go start a goroutine
	// 并发执行
	//go printHello()

	ch := make(chan string)
	// 多个goroutine
	for i:=0; i<5000 ; i++ {
		go printHello(i, ch)
	}
	// time.Sleep(time.Millisecond)
	// 接收并发执行中的结果
	for  {
		msg := <-ch
		fmt.Println(msg)
	}
}

func printHello(i int, ch chan string)  {
	for {
		ch <- fmt.Sprintf("from goroutine %d!\n", i)
	}

}

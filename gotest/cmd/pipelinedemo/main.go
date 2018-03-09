package main

import (
	"gotest/pipeline"
	"fmt"
	"os"
	"bufio"
)

func main() {
	const filename = "large.in"
	const n = 100000000
	file,err := os.Create(filename)
	if err != nil {
		panic(err)
	}

	defer file.Close()// 类似Java中的finally

	p := pipeline.RandomSource(n)

	writer := bufio.NewWriter(file)
	pipeline.WriterSink(writer, p)
	defer writer.Flush()
	//file.Close()

	// 读入small.in
	file,err = os.Open(filename)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	p = pipeline.ReaderSource(bufio.NewReader(file),-1)
	count := 0
	for v := range p {
		fmt.Println(v)
		count++
		if count >= 100 {
			break
		}
	}
}

func MergeDemo()  {
	p :=pipeline.Merge(pipeline.InMemorySort(pipeline.ArraySource(3,2,6,7,4)),
		pipeline.InMemorySort(pipeline.ArraySource(1,2,8,9,4,10,6)))
	//for {
	//	if num,ok := <- p;ok {
	//		fmt.Println(num)
	//	} else {
	//		break
	//	}
	//
	//}

	for v:= range p{
		fmt.Println(v)
	}
}
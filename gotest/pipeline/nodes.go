package pipeline

import (
	"sort"
	"io"
	"encoding/binary"
	"math/rand"
	"time"
	"fmt"
)

var startTime time.Time

func Init()  {
	startTime = time.Now()
}

// 这是一个节点库，作用将数组中的元素装送到一个channel中

//   函数名        参数       返回值
func ArraySource(a ...int) <-chan int {
	out := make(chan int)
	go func() {
		for _,v:= range a{
			out <- v
		}
		// 标记数据传送完毕
		close(out)
	}()

	return out
}

func InMemorySort(in <-chan int) <-chan int {
	out := make(chan int,1024)
	go func() {
		// read into memory
		var a []int
		for v:= range in {
			a = append(a ,v)
		}

		fmt.Println("Read done:",time.Now().Sub(startTime))
		// sort
		sort.Ints(a)
		fmt.Println("InMemorySort done:",time.Now().Sub(startTime))
		// output
		for _,v := range a {
			out <- v
		}


		close(out)

	}()
	return out
}

// 归并节点
func Merge(in1, in2<-chan int) <-chan int  {
	out := make(chan int,1024)
	go func() {
		v1, ok1 := <-in1
		v2, ok2 := <-in2
		for ok2 || ok1  {
			if !ok2 || (ok1 && v1 <= v2) {
				out <-v1
				v1, ok1 = <-in1
			} else {
				out <-v2
				v2,ok2 = <-in2
			}
		}
		close(out)
		fmt.Println("Merge done:",time.Now().Sub(startTime))
	}()

	return out
}

// 归并N个输入
func MergeN(inputs ...<-chan int) <-chan int {
	if len(inputs) == 1 {
		return inputs[0]
	}

	m := len(inputs) / 2
	// merge inputs[0,m) and inputs[m,end)
	return Merge(
		MergeN(inputs[:m]...),
		MergeN(inputs[m:]...))
}

// 读取数据源，并返回数据
// 分块读取
func ReaderSource(reader io.Reader, chunkSize int) <-chan int {
	out := make(chan int,1024)
	go func() {
		buffer := make([]byte,8)
		bytesRead := 0
		for {
			n, err := reader.Read(buffer)
			bytesRead += n
			if n > 0 {
				v := int(binary.BigEndian.Uint64(buffer))
				out <- v
			}
			if err != nil || (chunkSize != -1 && bytesRead >= chunkSize){
				break
			}
		}
		close(out)
	}()
	
	return out
}

// 将输入的数据源写入
func WriterSink(writer io.Writer, in <-chan int)  {
	for v := range in{
		buffer := make([]byte,8)
		binary.BigEndian.PutUint64(buffer,uint64(v))
		writer.Write(buffer)
	}
}

// 输出随机数，用于测试
func RandomSource(count int) <-chan int {
	out := make(chan int)
	go func() {
		for i := 0; i < count; i++  {
			out <- rand.Int()
		}
		close(out)
	}()

	return out
}
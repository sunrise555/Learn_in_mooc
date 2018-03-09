package main

import (
	"os"
	"gotest/pipeline"
	"bufio"
	"fmt"
	"strconv"
	//"time"
)

/**
完成外部排序
 */
func main() {
	p := createPipeline("large.in", 800000000,4)
	writeToFile(p, "large.out")
	printFile("large.out")
	//createNetworkPipeline("small.in", 512,4)
	//time.Sleep(time.Hour)
}
func writeToFile(p <-chan int, filename string)  {
	file, err := os.Create(filename)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	writer := bufio.NewWriter(file)
	defer writer.Flush()

	pipeline.WriterSink(writer, p)
}
func printFile(filename string) {
	file, err := os.Open(filename)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	p := pipeline.ReaderSource(bufio.NewReader(file),-1)

	count := 0
	for v := range p {
		fmt.Println(v)
		count++
		if count >= 100{
			break
		}
	}
}

// 单机版外部排序
func createPipeline(filename string, fileSize,chunkCount int) <-chan int {
	// 获取每一块的大小
	chunkSize := fileSize / chunkCount

	pipeline.Init()
	// 收集每一块排序后的结果
	//sortResults := []<-chan int{}
	var sortResults []<-chan int
	for i := 0; i < chunkCount; i++ {
		file, err := os.Open(filename)
		if err != nil {
			panic(err)
		}

		// 找到每一块的索引
		file.Seek(int64(i * chunkSize),0)

		source := pipeline.ReaderSource(bufio.NewReader(file), chunkSize)

		sortResults = append(sortResults,pipeline.InMemorySort(source))

	}
	return pipeline.MergeN(sortResults...)

}

// 网络版外部排序
func createNetworkPipeline(filename string, fileSize,chunkCount int) <-chan int {
	// 获取每一块的大小
	chunkSize := fileSize / chunkCount

	pipeline.Init()
	// 收集每一块排序后的结果
	//sortResults := []<-chan int{}
	var sortAddr []string
	for i := 0; i < chunkCount; i++ {
		file, err := os.Open(filename)
		if err != nil {
			panic(err)
		}

		// 找到每一块的索引
		file.Seek(int64(i * chunkSize),0)

		source := pipeline.ReaderSource(bufio.NewReader(file), chunkSize)

		addr := ":" + strconv.Itoa(7000 + i)
		pipeline.NetworkSink(addr, pipeline.InMemorySort(source))

		sortAddr = append(sortAddr,addr)
	}
	var sortResults []<-chan int

	for _, addr := range sortAddr{
		sortResults = append(sortResults,pipeline.NetworkSource(addr))
	}
	return pipeline.MergeN(sortResults...)

}
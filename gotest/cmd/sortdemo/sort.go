package main

import (
	"sort"
	"fmt"
)

func main() {

	a := []int{3,6,4,7,5,8}
	sort.Ints(a)

	//for i,v := range a {
	//	fmt.Println(i,v)
	//}

	for _,v:=range a {
		fmt.Println(v)
	}
}

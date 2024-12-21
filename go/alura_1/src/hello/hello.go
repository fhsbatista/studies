package main

import "fmt"
import "reflect"

func main() {
	var name = "Fernando"
	lastname := "Batista"
	var age = 29
	var version = 1.1

	fmt.Println("Hello Mr.", name, lastname, ", Age: ", age)
	fmt.Println("This program version is", version)

	fmt.Println("Name var type is", reflect.TypeOf(name))
	fmt.Println("Age var type is", reflect.TypeOf(age))
	fmt.Println("Version var type is", reflect.TypeOf(version))
}

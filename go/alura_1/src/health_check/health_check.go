package main

import "fmt"

func main() {
	name := "Fernnado"
	version := "1.0"

	fmt.Println("Hello, Mr.", name)
	fmt.Println("This program's version is", version)

	fmt.Println()
	fmt.Println()
	fmt.Println()

	fmt.Println("1 - Start monitoring")
	fmt.Println("2 - Show logs")
	fmt.Println("0 - Exit")

	var command int
	fmt.Scan(&command)

	if command == 1 {
		fmt.Println("Monitoring started")
	} else if command == 2 {
		fmt.Println("Showing logs")
	} else if command == 0 {
		fmt.Println("Exiting")
	} else {
		fmt.Println("Invalid option")
	}
}
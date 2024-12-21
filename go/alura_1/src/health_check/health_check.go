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

	// if command == 1 {
	// 	fmt.Println("Monitoring started")
	// } else if command == 2 {
	// 	fmt.Println("Showing logs")
	// } else if command == 0 {
	// 	fmt.Println("Exiting")
	// } else {
	// 	fmt.Println("Invalid option")
	// }

	switch command {
	case 1:
		// break -> "break" is not mandatory in Go. 
		// The case used will be the first one which condition is true. 
		// Only the first. Therefore "break" is not necessary.
		fmt.Println("Monitoring started")
	case 2:
		fmt.Println("Showing logs")
	case 0:
		fmt.Println("Exiting")
	default:
		fmt.Println("Invalid option")
	}
}
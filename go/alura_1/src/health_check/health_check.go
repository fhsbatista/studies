package main

import "fmt"
import "os"

func main() {
	showIntroduction()
	showMenu()

	command := readCommand()

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
		os.Exit(0)
	default:
		fmt.Println("Invalid option")
	}
}

func showIntroduction() {
	name := "Fernnado"
	version := "1.0"

	fmt.Println("Hello, Mr.", name)
	fmt.Println("This program's version is", version)

	fmt.Println()
	fmt.Println()
	fmt.Println()
}

func showMenu() {
	fmt.Println("1 - Start monitoring")
	fmt.Println("2 - Show logs")
	fmt.Println("0 - Exit")
}

func readCommand() int {
	var command int
	fmt.Scan(&command)

	return command
}
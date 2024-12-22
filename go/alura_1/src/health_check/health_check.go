package main

import "fmt"
import "os"
import "net/http"

func main() {
	showIntroduction()

	for {
		showMenu()
		command := readCommand()

		switch command {
		case 1:
			// break -> "break" is not mandatory in Go.
			// The case used will be the first one which condition is true.
			// Only the first. Therefore "break" is not necessary.
			monitor()
		case 2:
			fmt.Println("Showing logs")
		case 0:
			fmt.Println("Exiting")
			os.Exit(0) //0 to the OS means that everything went fine.
		default:
			fmt.Println("Invalid option")
			os.Exit(-1) //-1 to the OS means that something went wrong.
		}
	}
}

func showIntroduction() {
	//"_" can be used to ommit value returned by a function
	name, _, version := getUserInfo()

	fmt.Println("Hello, Mr.", name)
	fmt.Println("This program's version is", version)

	fmt.Println()
	fmt.Println()
	fmt.Println()
}

func getUserInfo() (string, int, float32) {
	return "Fernando", 29, 1.0
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

func monitor() {
	fmt.Println("Monitoring started")
	urls := []string{
		"https://github.com",
		"https://go.dev",
		"https://google.com",
		"https://agilemanifesto.org",
	}

	url := urls[0]
	resp, _ := http.Get(url)

	if resp.StatusCode == 200 {
		fmt.Println(url, "OK")
	} else {
		fmt.Println(url, "NOT OK")
	}
}

package main

import (
	"bufio"
	"bytes"
	"fmt"
	"io"
	"net/http"
	"os"
	"strconv"
	"strings"
	"time"
)

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
			printLogs()
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
	urls := getWebsitesUrls()

	for _, url := range urls {
		resp, err := http.Get(url)

		if err != nil {
			fmt.Println("Failure on http request:", err)
			return
		}

		if resp.StatusCode == 200 {
			fmt.Println(url, "OK")
			log(url, true)
		} else {
			fmt.Println(url, "NOT OK")
			log(url, false)
		}
	}
}

func getWebsitesUrls() []string {
	var urls []string

	file, err := os.ReadFile("urls.txt")

	if err != nil {
		fmt.Println("Something went wrong:", err)
	}

	reader := bufio.NewReader(bytes.NewReader(file))

	for {
		line, err := reader.ReadString('\n')
		line = strings.TrimSpace(line)
		urls = append(urls, line)

		if err == io.EOF {
			break
		}
	}

	return urls
}

func log(url string, status bool) {
	file, err := os.OpenFile("logs.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)

	if err != nil {
		fmt.Println(err)
	}

	time := time.Now().Format("02/01/2006 15:04:05")
	file.WriteString(time + " - " + url + " - online:" + strconv.FormatBool(status) + "\n")

	file.Close()
}

func printLogs() {
	fmt.Println("Showing logs")

	file, err := os.ReadFile("logs.txt")

	if err != nil {
		fmt.Println("Something went wrong:", err)
	}

	fmt.Println(string(file))
}

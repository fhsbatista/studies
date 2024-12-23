package main

import "fmt"

type Account struct {
	holder  string
	branch  int
	account int
	balance float64
}

func main() {
	account := Account{
		holder:  "Fernando Batista",
		branch:  123,
		account: 12345,
		balance: 100.00,
	}

	fmt.Println(account)
}

package main

import (
	"fmt"
)

type Account struct {
	holder  string
	branch  int
	account int
	balance float64
}

func (a *Account) Withdraw(value float64) {
	hasBalance := a.balance >= value

	if !hasBalance {
		fmt.Println("Could not withdraw because there is not sufficient balance") 
	}

	a.balance = a.balance - value
	fmt.Println("Withdraw Success") 
}

func (a *Account) BalanceInfo() {
	fmt.Println("Current balance:", a.balance)
}

func main() {
	account := Account{
		holder:  "Fernando Batista",
		branch:  123,
		account: 12345,
		balance: 100.00,
	}

	account.BalanceInfo()
	account.Withdraw(50.0)
	account.BalanceInfo()
}
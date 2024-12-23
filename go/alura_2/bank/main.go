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

func (a *Account) Withdraw(value float64) (string, float64) {
	validValue := value > 0
	if !validValue {
		return "Value must be greater than 0", a.balance
	}

	hasBalance := a.balance >= value
	if !hasBalance {
		return "Could not withdraw because there is not sufficient balance", a.balance
	}

	a.balance -= value
	return "Withdraw success", a.balance
}

func (a *Account) Deposit(value float64) (string, float64) {
	validValue := value > 0
	if !validValue {
		return "Value must be greater than 0", a.balance
	}

	a.balance += value
	return "Deposit succees", a.balance
}

func (a *Account) Transfer(value float64, destination *Account) (string, float64) {
	validValue := value > 0
	if !validValue {
		return "Value must be greater than 0", a.balance
	}

	hasBalance := a.balance >= value
	if !hasBalance {
		return "Could not transfer because there is not sufficient balance", a.balance
	}

	a.balance -= value
	destination.balance += value

	return "Transfer success", a.balance
}

func (a *Account) BalanceInfo() {
	fmt.Println("Current balance:", a.balance)
}

func main() {
	account := Account{
		holder:  "Fernando Batista",
		branch:  123,
		account: 12345,
		balance: 0,
	}

	account2 := Account{
		holder:  "Batista Fernando",
		branch:  123,
		account: 12345,
		balance: 0,
	}

	fmt.Println(account.Deposit(0))
	fmt.Println(account.Deposit(200))
	fmt.Println(account.Withdraw(200))
	fmt.Println(account.Withdraw(200))

	fmt.Println(account.Deposit(200))
	fmt.Println(account.Transfer(140, &account2))

	account.BalanceInfo()
	account2.BalanceInfo()
}
package main

import (
	"fmt"
	"bank/accounts"
)

func BalanceInfo(account accounts.Account) {
	fmt.Println("Current Balance:", account.Balance)
}

func main() {
	account := accounts.Account{
		Holder:  "Fernando Batista",
		Branch:  123,
		Account: 12345,
		Balance: 0,
	}

	account2 := accounts.Account{
		Holder:  "Fernando Batista",
		Branch:  124,
		Account: 12345,
		Balance: 0,
	}

	fmt.Println(account.Deposit(0))
	fmt.Println(account.Deposit(200))
	fmt.Println(account.Withdraw(200))
	fmt.Println(account.Withdraw(200))

	fmt.Println(account.Deposit(200))
	fmt.Println(account.Transfer(140, &account2))

	BalanceInfo(account)
	BalanceInfo(account2)
}
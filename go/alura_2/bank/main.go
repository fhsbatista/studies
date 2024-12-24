package main

import (
	"bank/accounts"
	"bank/persons"
	"fmt"
)

func BalanceInfo(account accounts.Account) {
	fmt.Println("Current Balance:", account.Balance)
}

func main() {
	account := accounts.Account{
		Holder: persons.Person{
			Name:       "Fernando Batista",
			CPF:        "123.456.789-00",
			Profession: "Software Engineer",
		},
		Branch:  123,
		Account: 12345,
		Balance: 0,
	}

	account2 := accounts.Account{
		Holder: persons.Person{
			Name:       "Batista Fernando",
			CPF:        "321.456.789-00",
			Profession: "UX Designer",
		},
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

package main

import (
	"bank/accounts"
	"bank/persons"
	"fmt"
)

func BalanceInfo(account account) {
	fmt.Println("Current Balance:", account.Balance())
}

type account interface {
	Withdraw(value float64) (string, float64)
	Balance() float64
}

func PayBoleto(account account, value float64) {
	account.Withdraw(value)
}

func main() {
	checkingAccount := accounts.CheckingAccount{
		Holder: persons.Person{
			Name:       "Fernando Batista",
			CPF:        "123.456.789-00",
			Profession: "Software Engineer",
		},
		Branch:  123,
		Account: 12345,
	}

	checkingAccount2 := accounts.CheckingAccount{
		Holder: persons.Person{
			Name:       "Batista Fernando",
			CPF:        "321.456.789-00",
			Profession: "UX Designer",
		},
		Branch:  124,
		Account: 12345,
	}

	fmt.Println(checkingAccount.Deposit(0))
	fmt.Println(checkingAccount.Deposit(200))
	fmt.Println(checkingAccount.Withdraw(200))
	fmt.Println(checkingAccount.Withdraw(200))

	fmt.Println(checkingAccount.Deposit(200))
	fmt.Println(checkingAccount.Transfer(140, &checkingAccount2))

	BalanceInfo(&checkingAccount)
	BalanceInfo(&checkingAccount2)

	fmt.Println("Saving account examples")

	savingAccount := accounts.SavingAccount{
		Holder: persons.Person{
			Name:       "Fernando Batista",
			CPF:        "123.456.789-00",
			Profession: "Software Engineer",
		},
		Branch:  123,
		Account: 12345,
	}

	fmt.Println(savingAccount.Deposit(0))
	fmt.Println(savingAccount.Deposit(200))
	fmt.Println(savingAccount.Withdraw(200))
	fmt.Println(savingAccount.Withdraw(200))

	fmt.Println(savingAccount.Deposit(200))

	PayBoleto(&checkingAccount, 100)
	PayBoleto(&savingAccount, 100)
	PayBoleto(&savingAccount, 500)

	BalanceInfo(&checkingAccount)
	BalanceInfo(&savingAccount)
}

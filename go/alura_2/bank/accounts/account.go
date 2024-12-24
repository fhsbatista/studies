package accounts

import "bank/persons"

type Account struct {
	Holder          persons.Person
	Branch, Account int
	balance         float64
}

func (a *Account) Withdraw(value float64) (string, float64) {
	validValue := value > 0
	if !validValue {
		return "Value must be greater than 0", a.balance
	}

	hasbalance := a.balance >= value
	if !hasbalance {
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

	hasbalance := a.balance >= value
	if !hasbalance {
		return "Could not transfer because there is not sufficient balance", a.balance
	}

	a.balance -= value
	destination.balance += value

	return "Transfer success", a.balance
}

func (a *Account) Balance() float64 {
	return a.balance
}

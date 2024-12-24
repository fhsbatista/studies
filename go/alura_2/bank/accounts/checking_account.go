package accounts

import "bank/persons"

type CheckingAccount struct {
	Holder          persons.Person
	Branch, Account int
	balance         float64
}

func (a *CheckingAccount) Withdraw(value float64) (string, float64) {
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

func (a *CheckingAccount) Deposit(value float64) (string, float64) {
	validValue := value > 0
	if !validValue {
		return "Value must be greater than 0", a.balance
	}

	a.balance += value
	return "Deposit succees", a.balance
}

func (a *CheckingAccount) Transfer(value float64, destination *CheckingAccount) (string, float64) {
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

func (a *CheckingAccount) Balance() float64 {
	return a.balance
}

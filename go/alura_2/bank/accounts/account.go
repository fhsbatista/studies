package accounts

import "bank/persons"

type Account struct {
	Holder  persons.Person
	Branch  int
	Account int
	Balance float64
}

func (a *Account) Withdraw(value float64) (string, float64) {
	validValue := value > 0
	if !validValue {
		return "Value must be greater than 0", a.Balance
	}

	hasBalance := a.Balance >= value
	if !hasBalance {
		return "Could not withdraw because there is not sufficient Balance", a.Balance
	}

	a.Balance -= value
	return "Withdraw success", a.Balance
}

func (a *Account) Deposit(value float64) (string, float64) {
	validValue := value > 0
	if !validValue {
		return "Value must be greater than 0", a.Balance
	}

	a.Balance += value
	return "Deposit succees", a.Balance
}

func (a *Account) Transfer(value float64, destination *Account) (string, float64) {
	validValue := value > 0
	if !validValue {
		return "Value must be greater than 0", a.Balance
	}

	hasBalance := a.Balance >= value
	if !hasBalance {
		return "Could not transfer because there is not sufficient Balance", a.Balance
	}

	a.Balance -= value
	destination.Balance += value

	return "Transfer success", a.Balance
}

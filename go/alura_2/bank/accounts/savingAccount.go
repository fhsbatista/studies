package accounts

import "bank/persons"

type SavingAccount struct {
	Holder                     persons.Person
	Branch, Account, Operation int
	balance                    float64
}

func (a *SavingAccount) Withdraw(value float64) (string, float64) {
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

func (a *SavingAccount) Deposit(value float64) (string, float64) {
	validValue := value > 0
	if !validValue {
		return "Value must be greater than 0", a.balance
	}

	a.balance += value
	return "Deposit succees", a.balance
}

func (a *SavingAccount) Balance() float64 {
	return a.balance
}

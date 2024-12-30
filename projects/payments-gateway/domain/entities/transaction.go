package entities

import "errors"

type Transaction struct {
	ID string
	Account string
	Amount float64
	Status string
	CreditCard CreditCard
	ErrorMessage string
}

func NewTransaction() *Transaction {
	return &Transaction{}
}

func (t *Transaction) IsValid() error {
	if t.Amount > 1000 {
		return errors.New("no limit for this transaction")
	}

	return nil
}
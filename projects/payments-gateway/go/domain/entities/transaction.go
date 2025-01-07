package entities

import "errors"

const (
	REJECTED = "rejected"
	APPROVED = "approved"
)

type Transaction struct {
	ID           string
	Account      string
	Amount       float64
	Status       string
	CreditCard   CreditCard
	ErrorMessage string
}

func NewTransaction() *Transaction {
	return &Transaction{}
}

func (t *Transaction) IsValid() error {
	if t.Amount > 1000 {
		return errors.New("no limit for this transaction")
	}

	if t.Amount < 1 {
		return errors.New("amount must be greater than 1")
	}

	return nil
}

func (t *Transaction) SetCreditCard(card CreditCard) {
	t.CreditCard = card
}

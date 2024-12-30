package entities

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
	return nil
}
package transactions

import (
	"encoding/json"
	"payments-gateway/domain/usecases/process_transaction"
)

type KafkaPresenter struct {
	ID string `json:"id"`
	Status string `json:"status"`
	ErrorMessage string `json:"error_message"`
}

func NewTransactionKafkaPresenter() *KafkaPresenter {
	return &KafkaPresenter {}
}

func (p *KafkaPresenter) Bind(input interface{}) error {
	p.ID = input.(process_transaction.TransactionDtoOutput).ID
	p.Status = input.(process_transaction.TransactionDtoOutput).Status
	p.ErrorMessage = input.(process_transaction.TransactionDtoOutput).ErrorMessage

	return nil
}

func (p *KafkaPresenter) Show() ([]byte, error) {
	json, err := json.Marshal(p)
	if err != nil {
		return nil, err
	}

	return json, nil
}


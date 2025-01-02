package process_transaction

import (
	"payments-gateway/adapters/broker"
	"payments-gateway/domain/entities"
	"payments-gateway/domain/repositories"
)

type ProcessTransaction struct {
	Repository repositories.TransactionRepository
	Producer   broker.ProducerInterface
	Topic      string
}

func NewProcessTransaction(
	repository repositories.TransactionRepository,
	producerInterface broker.ProducerInterface,
	topic string) *ProcessTransaction {

	return &ProcessTransaction{
		Repository: repository,
		Producer:   producerInterface,
		Topic:      topic}
}

func (p *ProcessTransaction) Execute(input TransactionDtoInput) (TransactionDtoOutput, error) {
	transaction := entities.NewTransaction()
	transaction.ID = input.ID
	transaction.Account = input.AccountID
	transaction.Amount = input.Amount

	card, invalidCard := entities.NewCreditCard(
		input.CreditCardNumber,
		input.CreditCardName,
		input.CreditCardExpirationMonth,
		input.CreditCardExpirationYear,
		input.CreditCardCvv)

	if invalidCard != nil {
		return p.rejectTransaction(transaction, invalidCard)
	}

	transaction.SetCreditCard(*card)
	invalidTransaction := transaction.IsValid()

	if invalidTransaction != nil {
		return p.rejectTransaction(transaction, invalidTransaction)
	}

	return p.approve(transaction)
}

func (p *ProcessTransaction) rejectTransaction(transaction *entities.Transaction, error error) (TransactionDtoOutput, error) {
	err := p.Repository.Insert(
		transaction.ID,
		transaction.Account,
		transaction.Amount,
		entities.REJECTED,
		error.Error())

	if err != nil {
		return TransactionDtoOutput{}, err
	}

	output := TransactionDtoOutput{
		ID:           transaction.ID,
		Status:       entities.REJECTED,
		ErrorMessage: error.Error(),
	}

	err = p.publish(output, []byte(transaction.ID))
	if err != nil {
		return TransactionDtoOutput{}, err
	}

	return output, nil
}

func (p *ProcessTransaction) approve(transaction *entities.Transaction) (TransactionDtoOutput, error) {
	err := p.Repository.Insert(
		transaction.ID,
		transaction.Account,
		transaction.Amount,
		entities.APPROVED,
		"")

	if err != nil {
		return TransactionDtoOutput{}, err
	}

	output := TransactionDtoOutput{
		ID:           transaction.ID,
		Status:       entities.APPROVED,
		ErrorMessage: "",
	}

	err = p.publish(output, []byte(transaction.ID))
	if err != nil {
		return TransactionDtoOutput{}, err
	}

	return output, nil
}

func (p *ProcessTransaction) publish(output TransactionDtoOutput, key []byte) error {
	err := p.Producer.Publish(output, key, p.Topic)
	if err != nil {
		return err
	}

	return nil
}

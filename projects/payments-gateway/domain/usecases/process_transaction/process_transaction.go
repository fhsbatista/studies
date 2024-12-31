package process_transaction

import (
	"payments-gateway/domain/entities"
	"payments-gateway/domain/repositories"
)

type ProcessTransaction struct {
	Repository repositories.TransactionRepository
}

func NewProcessTransaction(repository repositories.TransactionRepository) *ProcessTransaction {
	return &ProcessTransaction{Repository: repository}
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
		err := p.Repository.Insert(
			transaction.ID,
			transaction.Account,
			transaction.Amount,
			entities.REJECTED,
			invalidCard.Error())

		if err != nil {
			return TransactionDtoOutput{}, err
		}

		output := TransactionDtoOutput{
			ID:           transaction.ID,
			Status:       entities.REJECTED,
			ErrorMessage: invalidCard.Error(),
		}

		return output, nil
	}

	transaction.SetCreditCard(*card)
	invalidTransaction := transaction.IsValid()

	if invalidTransaction != nil {
		err := p.Repository.Insert(
			transaction.ID,
			transaction.Account,
			transaction.Amount,
			entities.REJECTED,
			invalidTransaction.Error())

		if err != nil {
			return TransactionDtoOutput{}, err
		}

		output := TransactionDtoOutput{
			ID:           transaction.ID,
			Status:       entities.REJECTED,
			ErrorMessage: invalidTransaction.Error(),
		}

		return output, nil
	}

	return TransactionDtoOutput{}, nil
}

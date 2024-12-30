package entities

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestTranscation_IsValid(t *testing.T) {
	transaction := NewTransaction()
	transaction.ID = "1"
	transaction.Account = "1"
	transaction.Amount = 120

	assert.Nil(t, transaction.IsValid())
}

func TestTransaction_IsNotValid_AmountGreaterThan1000(t *testing.T) {
	transaction := NewTransaction()
	transaction.ID = "1"
	transaction.Account = "1"
	transaction.Amount = 1001

	err := transaction.IsValid()
	assert.Error(t, err)
	assert.Equal(t, "no limit for this transaction", err.Error())
}

func TestTransaction_IsNotValid_AmountGreaterLess1(t *testing.T) {
	transaction := NewTransaction()
	transaction.ID = "1"
	transaction.Account = "1"
	transaction.Amount = 0

	err := transaction.IsValid()
	assert.Error(t, err)
	assert.Equal(t, "amount must be greater than 1", err.Error())
}
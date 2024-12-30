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
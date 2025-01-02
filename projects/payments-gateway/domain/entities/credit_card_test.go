package entities

import (
	"testing"
	"time"

	"github.com/stretchr/testify/assert"
)

func TestCreditCardNumber(t *testing.T) {
	_, err := NewCreditCard("40000000000000000", "José Silveira", 12, currentYear(), 123)
	assert.Equal(t, "invalid credit card number", err.Error())

	_, err = NewCreditCard("4193523830170205", "José Silveira", 12, currentYear(), 123)
	assert.Nil(t, err)
}

func TestCreditCardExpirationMonth(t *testing.T) {
	_, err := NewCreditCard("4193523830170205", "José Silveira", 13, currentYear(), 123)
	assert.Equal(t, "invalid expiration month", err.Error())

	_, err = NewCreditCard("4193523830170205", "José Silveira", 0, currentYear(), 123)
	assert.Equal(t, "invalid expiration month", err.Error())

	_, err = NewCreditCard("4193523830170205", "José Silveira", 12, currentYear(), 123)
	assert.Nil(t, err)
}

func TestCreditCardExpirationYear(t *testing.T) {
	lastYear := time.Now().AddDate(-1, 0, 0)
	_, err := NewCreditCard("4193523830170205", "José Silveira", 12, lastYear.Year(), 123)
	assert.Equal(t, "invalid expiration year", err.Error())

	_, err = NewCreditCard("4193523830170205", "José Silveira", 12, currentYear(), 123)
	assert.Nil(t, err)

	nextYear := time.Now().AddDate(1, 0, 0)
	_, err = NewCreditCard("4193523830170205", "José Silveira", 12, nextYear.Year(), 123)
	assert.Nil(t, err)
}

func currentYear() int {
	return time.Now().Year()
}

package entities

import (
	"errors"
	"regexp"
)

type CreditCard struct {
	number          string
	name            string
	expirationMonth int
	expirationYear  int
	cvv             int
}

func NewCreditCard(
	number string,
	name string,
	expirationMonth int,
	expirationYear int,
	cvv int,
) (*CreditCard, error) {
	card := &CreditCard{
		number:          number,
		name:            name,
		expirationMonth: expirationMonth,
		expirationYear:  expirationYear,
		cvv:             cvv,
	}

	err := card.IsValid()
	if err != nil {
		return nil, err
	}

	return card, nil
}

func (c *CreditCard) IsValid() error {
	if err := c.validateNumber(); err != nil {
		return err
	}

	if err := c.validateMonth(); err != nil {
		return err
	}

	return nil
}

func (c *CreditCard) validateNumber() error {
	numberRegexp := regexp.MustCompile(`^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|6(?:011|5[0-9]{2})[0-9]{12}|(?:2131|1800|35\d{3})\d{11})$`)

	if !numberRegexp.MatchString(c.number) {
		return errors.New("invalid credit card number")
	}

	return nil
}

func (c *CreditCard) validateMonth() error {
	return errors.New("invalid expiration month")
}

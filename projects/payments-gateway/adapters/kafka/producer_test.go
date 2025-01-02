package kafka

import (
	"payments-gateway/adapters/presenters/transactions"
	"payments-gateway/domain/entities"
	"payments-gateway/domain/usecases/process_transaction"
	"testing"

	//instalar essa dependencia
	ckafka "github.com/confluentinc/confluent-kafka-go/kafka"
	"github.com/stretchr/testify/assert"
)

func TestProducerPublish(t *testing.T) {
	expectedOutput := process_transaction.TransactionDtoOutput{
		ID:           "1",
		Status:       entities.REJECTED,
		ErrorMessage: "no limit for this transaction",
	}

	// outputJson, _ := json.Marshal(expectedOutput)

	configMap := ckafka.ConfigMap{
		"test.mock.num.brokers": 3,
	}
	producer := NewKafkaProducer(&configMap, transactions.NewTransactionKafkaPresenter())
	err := producer.Publish(expectedOutput, []byte("1"), "test")

	assert.Nil(t, err)
}
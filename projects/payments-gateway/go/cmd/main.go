package main

import (
	"database/sql"
	"encoding/json"
	"log"
	"payments-gateway/adapters/broker/kafka"
	"payments-gateway/adapters/factories"
	"payments-gateway/adapters/presenters/transactions"
	"payments-gateway/domain/usecases/process_transaction"

	ckafka "github.com/confluentinc/confluent-kafka-go/kafka"
	_ "github.com/mattn/go-sqlite3"
)

func main() {
	db, err := sql.Open("sqlite3", "test.db")
	if err != nil {
		log.Fatal(err)
	}

	repositoryFactory := factories.NewRepositoryDatabaseFactory(db)
	repository := repositoryFactory.CreateTransactionRepository()

	configMapProducer := &ckafka.ConfigMap{
		"bootstrap.servers": "kafka:9092",
	}
	kafkaPresenter := transactions.NewTransactionKafkaPresenter()
	producer := kafka.NewKafkaProducer(configMapProducer, kafkaPresenter)

	msgChannel := make(chan *ckafka.Message)
	configMapConsumer := &ckafka.ConfigMap{
		"bootstrap.servers": "kafka:9092",
		"client.id":         "goapp",
		"group.id":          "goapp",
	}
	topics := []string{"transactions"}
	consumer := kafka.NewConsumer(configMapConsumer, topics)
	go consumer.Consume(msgChannel)

	usecase := process_transaction.NewProcessTransaction(repository, producer, "transactions_result")

	for msg := range msgChannel {
		var input process_transaction.TransactionDtoInput
		json.Unmarshal(msg.Value, &input)

		usecase.Execute(input)
	}
}

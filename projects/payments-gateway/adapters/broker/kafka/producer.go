package kafka

import (
	"payments-gateway/adapters/presenters"

	ckafka "github.com/confluentinc/confluent-kafka-go/kafka"
)


type Producer struct {
	ConfigMap *ckafka.ConfigMap
	Presenter presenters.Presenter
}

func NewKafkaProducer(configMap *ckafka.ConfigMap, presenter presenters.Presenter) *Producer {
	return &Producer{ConfigMap: configMap, Presenter: presenter}
}

func (p *Producer) Publish(msg interface{}, key []byte, topic string) error {
	producer, err := ckafka.NewProducer(p.ConfigMap)
	if err != nil {
		return err
	}

	err = p.Presenter.Bind(msg)
	if err != nil {
		return err
	}

	presenterMsg, err := p.Presenter.Show()
	if err != nil {
		return err
	}

	message := &ckafka.Message{
		TopicPartition: ckafka.TopicPartition{Topic: &topic, Partition: ckafka.PartitionAny},
		Value: presenterMsg,
		Key: key,
	}

	err = producer.Produce(message, nil)
	if err != nil {
		return err
	}

	return nil
}
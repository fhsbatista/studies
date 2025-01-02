package broker

type ProducerInterface interface {
	Publish(msg interface{}, key []byte, topics string) error
}
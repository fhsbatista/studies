package presenters

type Presenter interface {
	Show() ([]byte, error)
	Bind(interface{}) error
}
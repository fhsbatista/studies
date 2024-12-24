package models

import "go_web/db"

type Product struct {
	Id          int
	Name        string
	Description string
	Price       float64
	Quantity    int
}

func FindAllProducts() []Product {
	db := db.ConnectDatabase()

	query, err := db.Query("select * from products")

	if err != nil {
		panic(err.Error())
	}

	products := []Product{}

	for query.Next() {
		var id, quantity int
		var name, description string
		var price float64

		err = query.Scan(&id, &name, &description, &price, &quantity)

		if err != nil {
			panic(err.Error())
		}

		products = append(products, Product{id, name, description, price, quantity})
	}

	defer db.Close()

	return products
}

func NewProduct(name, description string, price float64, quantity int) {
	db := db.ConnectDatabase()

	query, err := db.Prepare("insert into products (name, description, price, quantity) values($1, $2, $3, $4)")

	if err != nil {
		panic(err.Error())
	}

	query.Exec(name, description, price, quantity)

	defer db.Close()
}

func DeleteProduct(productId string) {
	db := db.ConnectDatabase()

	query, err := db.Prepare("delete from products where id = $1")

	if err != nil {
		panic(err.Error())
	}

	query.Exec(productId)

	defer db.Close()
}

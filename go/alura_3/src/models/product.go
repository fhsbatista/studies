package models

import (
	"go_web/db"
)

type Product struct {
	Id          int
	Name        string
	Description string
	Price       float64
	Quantity    int
}

func FindProductById(productId int) Product {
	db := db.ConnectDatabase()

	query := db.QueryRow("select * from products where id = $1", productId)

	var product Product
	err := query.Scan(
		&product.Id,
		&product.Name,
		&product.Description,
		&product.Price,
		&product.Quantity,
	)

	if err != nil {
		panic(err.Error())
	}

	defer db.Close()

	return product
}

func FindAllProducts() []Product {
	db := db.ConnectDatabase()

	query, err := db.Query("select * from products order by id asc")

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

func EditProduct(id int, name, description string, price float64, quantity int) {
	db := db.ConnectDatabase()

	query, err := db.Prepare("update products set name = $2, description = $3, price = $4, quantity = $5 where id = $1")

	if err != nil {
		panic(err.Error())
	}

	query.Exec(id, name, description, price, quantity)

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

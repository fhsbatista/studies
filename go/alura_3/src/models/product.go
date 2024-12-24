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
	db := db.ConnectDatabaes()

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
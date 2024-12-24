package main

import (
	"database/sql"
	"html/template"
	"net/http"

	_ "github.com/lib/pq"
)

func connectDatabaes() *sql.DB {
	conn := "user=postgres dbname=go_web password=default-password host=localhost sslmode=disable"
	db, err := sql.Open("postgres", conn)

	if err != nil {
		panic(err.Error())
	}

	return db
}

type Product struct {
	Id          int
	Name        string
	Description string
	Price       float64
	Quantity    int
}

var templates = template.Must(template.ParseGlob("templates/*.html"))

func main() {
	http.HandleFunc("/", index)
	http.ListenAndServe(":8000", nil)
}

func index(w http.ResponseWriter, r *http.Request) {
	db := connectDatabaes()

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

	templates.ExecuteTemplate(w, "Index", products)

	defer db.Close()
}

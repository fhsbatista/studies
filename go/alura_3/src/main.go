package main

import (
	"database/sql"
	"html/template"
	"net/http"

	_ "github.com/lib/pq"
)

func connectDatabaes() *sql.DB {
	conn := "user=postgres dbname=go_web password=default-password host=localhost sslmode=disabled"
	db, err := sql.Open("postgres", conn)

	if err != nil {
		panic(err.Error())
	}

	return db
}

type Product struct {
	Name        string
	Description string
	Price       float64
	Quantity    int
}

var templates = template.Must(template.ParseGlob("templates/*.html"))

func main() {
	db := connectDatabaes()
	defer db.Close()
	http.HandleFunc("/", index)
	http.ListenAndServe(":8000", nil)
}

func index(w http.ResponseWriter, r *http.Request) {
	products := []Product{
		{"T-Shirt", "Oversized", 29.0, 10},
		{"Laptop", "Very fast", 2900.0, 10},
		{"Sneaker", "Confortable", 290.0, 10},
	}
	templates.ExecuteTemplate(w, "Index", products)
}

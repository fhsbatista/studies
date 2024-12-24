package main

import (
	"html/template"
	"net/http"
)

type Product struct {
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
	products := []Product{
		{"T-Shirt", "Oversized", 29.0, 10},
		{"Laptop", "Very fast", 2900.0, 10},
		{"Sneaker", "Confortable", 290.0, 10},
	}
	templates.ExecuteTemplate(w, "Index", products)
}

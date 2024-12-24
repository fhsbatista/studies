package controllers

import (
	"fmt"
	"go_web/models"
	"net/http"
	"strconv"
	"text/template"
)

var templates = template.Must(template.ParseGlob("templates/*.html"))

func Index(w http.ResponseWriter, r *http.Request) {
	products := models.FindAllProducts()
	templates.ExecuteTemplate(w, "Index", products)
}

func New(w http.ResponseWriter, r *http.Request) {
	templates.ExecuteTemplate(w, "New", nil)
}

func Edit(w http.ResponseWriter, r *http.Request) {
	id, err := strconv.Atoi(r.URL.Query().Get("id"))

	if err != nil {
		fmt.Println("Could not parse id")
	}

	product := models.FindProductById(id)
	templates.ExecuteTemplate(w, "Edit", product)
}

func Insert(w http.ResponseWriter, r *http.Request) {
	if r.Method == "POST" {
		name := r.FormValue("name")
		description := r.FormValue("description")
		price, errPrice := strconv.ParseFloat(r.FormValue("price"), 64)
		quantity, errQuantity := strconv.Atoi(r.FormValue("quantity"))

		if errPrice != nil {
			fmt.Println("Could not parse price")
		}

		if errQuantity != nil {
			fmt.Println("Could not parse quantity")
		}

		models.NewProduct(name, description, price, quantity)

		http.Redirect(w, r, "/", 301)
	}
}

func Update(w http.ResponseWriter, r *http.Request) {
	if r.Method == "POST" {
		id, errId := strconv.Atoi(r.FormValue("id"))
		name := r.FormValue("name")
		description := r.FormValue("description")
		price, errPrice := strconv.ParseFloat(r.FormValue("price"), 64)
		quantity, errQuantity := strconv.Atoi(r.FormValue("quantity"))

		if errId != nil {
			fmt.Println("Could not parse id")
		}

		if errPrice != nil {
			fmt.Println("Could not parse price")
		}

		if errQuantity != nil {
			fmt.Println("Could not parse quantity")
		}

		models.EditProduct(id, name, description, price, quantity)

		http.Redirect(w, r, "/", 301)
	}
}

func Delete(w http.ResponseWriter, r *http.Request) {
	productId := r.URL.Query().Get("id")
	models.DeleteProduct(productId)

	http.Redirect(w, r, "/", 301)
}
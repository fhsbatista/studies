package controllers

import (
	"go_web/models"
	"net/http"
	"text/template"
)

var templates = template.Must(template.ParseGlob("templates/*.html"))

func Index(w http.ResponseWriter, r *http.Request) {
	products := models.FindAllProducts()
	templates.ExecuteTemplate(w, "Index", products)
}

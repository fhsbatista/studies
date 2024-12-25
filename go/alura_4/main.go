package main

import (
	"api-rest/models"
	"api-rest/routes"
	"fmt"
)

func main() {
	models.Personalities = []models.Personality {
		{Name: "Name 1", Description: "some text"},
		{Name: "Name 2", Description: "some text"},
	}
	fmt.Println("Iniciando o servidor")
	routes.HandleRequest()
}
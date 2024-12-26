package main

import (
	"api-rest/database"
	"api-rest/models"
	"api-rest/routes"
	"fmt"
)

func main() {
	models.Personalities = []models.Personality {
		{Id: 1, Name: "Name 1", Description: "some text"},
		{Id: 2, Name: "Name 2", Description: "some text"},
	}
	database.ConnectDatabase()
	fmt.Println("Iniciando o servidor")
	routes.HandleRequest()
}
package main

import (
	"api-gin/database"
	"api-gin/models"
	"api-gin/routes"
)

func main() {
	database.ConnectDatabase()
	models.Students = []models.Student{
		{Name: "Fernando Batista", CPF: "12312312312", RG: "121231232"},
		{Name: "Fulano Batista", CPF: "12312312312", RG: "121231232"},
		{Name: "Ciclano Batista", CPF: "12312312312", RG: "121231232"},
	}
	routes.HandleRequests()
}

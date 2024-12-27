package main

import (
	"api-gin/database"
	"api-gin/routes"
)

func main() {
	database.ConnectDatabase()
	routes.HandleRequests()
}

package routes

import (
	"log"
	"net/http"
	"api-rest/controllers"
)

func HandleRequest() {
	http.HandleFunc("/", controllers.Home)
	http.HandleFunc("/api/personalities", controllers.Personalities)
	log.Fatal(http.ListenAndServe(":8000", nil))
}
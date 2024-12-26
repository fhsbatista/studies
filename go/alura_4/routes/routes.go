package routes

import (
	"api-rest/controllers"
	"log"
	"net/http"

	"github.com/gorilla/mux"
)

func HandleRequest() {
	router := mux.NewRouter()
	router.HandleFunc("/", controllers.Home)
	router.HandleFunc("/api/personalities", controllers.Personalities).Methods("Get")
	router.HandleFunc("/api/personalities/{id}", controllers.Personality).Methods("Get")
	router.HandleFunc("/api/personalities", controllers.CreatePersonality).Methods("Post")
	log.Fatal(http.ListenAndServe(":8000", router))
}
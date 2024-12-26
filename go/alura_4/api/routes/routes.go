package routes

import (
	"api-rest/controllers"
	"api-rest/middlewares"
	"log"
	"net/http"

	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
)

func HandleRequest() {
	router := mux.NewRouter()
	router.Use(middlewares.ContentType)
	router.HandleFunc("/", controllers.Home)
	router.HandleFunc("/api/personalities", controllers.Personalities).Methods("Get")
	router.HandleFunc("/api/personalities/{id}", controllers.Personality).Methods("Get")
	router.HandleFunc("/api/personalities", controllers.CreatePersonality).Methods("Post")
	router.HandleFunc("/api/personalities/{id}", controllers.EditPersonality).Methods("Put")
	router.HandleFunc("/api/personalities/{id}", controllers.DeletePersonality).Methods("Delete")
	log.Fatal(http.ListenAndServe(":8000", handlers.CORS(handlers.AllowedOrigins([]string{"*"})) (router)))
}

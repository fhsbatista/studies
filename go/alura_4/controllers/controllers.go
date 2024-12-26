package controllers

import (
	"api-rest/database"
	"api-rest/models"
	"encoding/json"
	"fmt"
	"net/http"

	"github.com/gorilla/mux"
)

func Home(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, "HomePage")
}

func Personalities(w http.ResponseWriter, r *http.Request) {
	var personalities []models.Personality
	database.DB.Find(&personalities)
	json.NewEncoder(w).Encode(personalities)
}

func Personality(w http.ResponseWriter, r *http.Request) {
	id := mux.Vars(r)["id"]

	var personality models.Personality
	database.DB.First(&personality, id)
	json.NewEncoder(w).Encode(personality)
}

func CreatePersonality(w http.ResponseWriter, r *http.Request) {
	var personality models.Personality
	json.NewDecoder(r.Body).Decode(&personality)

	database.DB.Create(&personality)
	json.NewEncoder(w).Encode(personality)
}

func DeletePersonality(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	
	id := mux.Vars(r)["id"]

	var response map[string]string

	if id == "" {
		w.WriteHeader(http.StatusBadRequest)
		response = map[string]string{"message": "Id parameter is missing"}
	}

	result := database.DB.Delete(&models.Personality{}, id)

	if result.Error != nil {
		response = map[string]string{"message": "Database error"}
		w.WriteHeader(http.StatusInternalServerError)
	} else {
		if result.RowsAffected > 0 {
			w.WriteHeader(http.StatusAccepted)
			response = map[string]string{"message": "Personality deleted!"}
		} else {
			w.WriteHeader(http.StatusNotFound)
			response = map[string]string{"message": "Couldn't find personality that matches id"}
		}
	}

	json.NewEncoder(w).Encode(response)
}

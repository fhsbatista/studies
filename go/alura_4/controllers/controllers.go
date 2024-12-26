package controllers

import (
	"api-rest/database"
	"api-rest/models"
	"encoding/json"
	"fmt"
	"net/http"

	"github.com/gorilla/mux"
)

type Response struct {
	Message string `json:"message"`
}

func Home(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, "HomePage")
}

func Personalities(w http.ResponseWriter, r *http.Request) {
	var personalities []models.Personality
	database.DB.Find(&personalities)

	RespondJson(w, r, http.StatusAccepted, personalities)
}

func Personality(w http.ResponseWriter, r *http.Request) {
	id := mux.Vars(r)["id"]

	var personality models.Personality
	database.DB.First(&personality, id)

	RespondJson(w, r, http.StatusAccepted, personality)
}

func CreatePersonality(w http.ResponseWriter, r *http.Request) {
	var personality models.Personality
	json.NewDecoder(r.Body).Decode(&personality)

	database.DB.Create(&personality)

	RespondJson(w, r, http.StatusAccepted, personality)
}

func DeletePersonality(w http.ResponseWriter, r *http.Request) {
	id := mux.Vars(r)["id"]

	var response Response

	if id == "" {
		response = Response{"Id parameter is missing"}
		RespondJson(w, r, http.StatusBadRequest, response)

	}

	result := database.DB.Delete(&models.Personality{}, id)

	if result.Error != nil {
		RespondJson(w, r, http.StatusInternalServerError, response)
		return
	} else {
		if result.RowsAffected > 0 {
			response = Response{"Personality deleted!"}
			RespondJson(w, r, http.StatusAccepted, response)
			return
		} else {
			response = Response{"Couldn't find personality that matches id"}
			RespondJson(w, r, http.StatusNotFound, response)
			return
		}
	}
}

func EditPersonality(w http.ResponseWriter, r *http.Request) {

	id := mux.Vars(r)["id"]

	var personality models.Personality
	database.DB.First(&personality, id)
	json.NewDecoder(r.Body).Decode(&personality)

	database.DB.Save(&personality)

	RespondJson(w, r, http.StatusAccepted, personality)
}

func RespondJson[T any](w http.ResponseWriter, r *http.Request, statusCode int, response T) {
	w.WriteHeader(statusCode)
	json.NewEncoder(w).Encode(response)
}

package controllers

import (
	"api-rest/models"
	"encoding/json"
	"fmt"
	"net/http"
	"strconv"

	"github.com/gorilla/mux"
)

func Home(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, "HomePage")
}

func Personalities(w http.ResponseWriter, r *http.Request) {
	json.NewEncoder(w).Encode(models.Personalities)
}

func Personality(w http.ResponseWriter, r *http.Request) {
	id := mux.Vars(r)["id"]

	for _, personality := range models.Personalities {
		if strconv.Itoa(personality.Id) == id {
			json.NewEncoder(w).Encode(personality)
		}
	}
}
package controllers

import (
	"encoding/json"
	"net/http"
)

func Home(w http.ResponseWriter, r *http.Request) {
	gateway := "paypal"

	if (gateway == "paypal") {
		RespondJson(w, r, http.StatusAccepted, "Pagando com paypal")
		return
	}

	RespondJson(w, r, http.StatusAccepted, "Pagando com gateway genérico")
}

func RespondJson[T any](w http.ResponseWriter, r *http.Request, statusCode int, response T) {
	w.WriteHeader(statusCode)
	json.NewEncoder(w).Encode(response)
}
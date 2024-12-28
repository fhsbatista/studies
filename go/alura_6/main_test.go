package main

import (
	"api-gin/controllers"
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/gin-gonic/gin"
)

func RoutesSetup() *gin.Engine {
	r := gin.Default()
	return r
}

func TestCheckStatusCodeOnGreetings(t *testing.T) {
	r := RoutesSetup()
	r.GET("/:nome", controllers.Greetings)
	
	request, _ := http.NewRequest("GET", "/fernando", nil)
	response := httptest.NewRecorder()
	r.ServeHTTP(response, request)

	if response.Code != http.StatusOK {
		t.Fatalf("response status code should be %d but it is %d instead", http.StatusOK, response.Code)
	}
}
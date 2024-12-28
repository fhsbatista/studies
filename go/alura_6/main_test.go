package main

import (
	"api-gin/controllers"
	"io"
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/gin-gonic/gin"
	"github.com/stretchr/testify/assert"
)

func RoutesSetup() *gin.Engine {
	r := gin.Default()
	return r
}

func TestCheckStatusCodeOnGreetings(t *testing.T) {
	r := RoutesSetup()
	r.GET("/greetings/:name", controllers.Greetings)
	
	request, _ := http.NewRequest("GET", "/greetings/fernando", nil)
	response := httptest.NewRecorder()
	r.ServeHTTP(response, request)

	assert.Equal(t, http.StatusOK, response.Code, "they should be equal")

	responseMock := `{"message":"Hello, fernando, how are u?"}`
	responseBody, _ := io.ReadAll(response.Body)

	assert.Equal(t, responseMock, string(responseBody))
}
package main

import (
	"api-gin/controllers"
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
	r.GET("/:nome", controllers.Greetings)
	
	request, _ := http.NewRequest("GET", "/fernando", nil)
	response := httptest.NewRecorder()
	r.ServeHTTP(response, request)

	assert.Equal(t, http.StatusOK, response.Code, "they should be equal")
}
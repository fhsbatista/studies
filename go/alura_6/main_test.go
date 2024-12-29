package main

import (
	"api-gin/controllers"
	"api-gin/database"
	"api-gin/models"
	"io"
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/gin-gonic/gin"
	"github.com/stretchr/testify/assert"
)

func RoutesSetup() *gin.Engine {
	gin.SetMode(gin.ReleaseMode)
	r := gin.Default()
	return r
}

var ID int

func CreateStudent() {
	student := models.Student{Name:"Student name", RG: "123456789", CPF: "12345678900"}
	database.DB.Create(&student)
	ID = int(student.ID)
}

func DeleteStudent() {
	database.DB.Delete(&models.Student{}, ID)
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

func TestListStudents(t *testing.T) {
	database.ConnectDatabase()

	CreateStudent()
	defer DeleteStudent()

	r := RoutesSetup()
	r.GET("/students", controllers.Students)

	request, _ := http.NewRequest("GET", "/students", nil)
	response := httptest.NewRecorder()
	r.ServeHTTP(response, request)

	assert.Equal(t, http.StatusOK, response.Code, "they should be equal")
}

func TestStudentByCpf(t *testing.T) {
	database.ConnectDatabase()
	
	CreateStudent()
	defer DeleteStudent()

	r := RoutesSetup()
	r.GET("/students/cpf/:cpf", controllers.StudentByCPF)

	request, _ := http.NewRequest("GET", "/students/cpf/12345678900", nil)
	response := httptest.NewRecorder()
	r.ServeHTTP(response, request)

	assert.Equal(t, http.StatusOK, response.Code, "they should be equal")

}
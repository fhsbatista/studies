package main

import (
	"api-gin/controllers"
	"api-gin/database"
	"api-gin/models"
	"bytes"
	"encoding/json"
	"io"
	"net/http"
	"net/http/httptest"
	"strconv"
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
	student := models.Student{Name: "Student name", RG: "123456789", CPF: "12345678900"}
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

func TestStudent(t *testing.T) {
	database.ConnectDatabase()

	CreateStudent()
	defer DeleteStudent()

	r := RoutesSetup()
	r.GET("/students/:id", controllers.Student)

	path := "/students/" + strconv.Itoa(ID)
	request, _ := http.NewRequest("GET", path, nil)
	response := httptest.NewRecorder()
	r.ServeHTTP(response, request)

	var responseStudent models.Student
	json.Unmarshal(response.Body.Bytes(), &responseStudent)

	assert.Equal(t, "Student name", responseStudent.Name)
	assert.Equal(t, "12345678900", responseStudent.CPF)
	assert.Equal(t, "123456789", responseStudent.RG)
	assert.Equal(t, http.StatusOK, response.Code)
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

func TestDeleteStudent(t *testing.T) {
	database.ConnectDatabase()

	CreateStudent()

	r := RoutesSetup()
	r.GET("/students", controllers.Students)
	r.DELETE("/students/:id", controllers.DeleteStudent)

	path := "/students/" + strconv.Itoa(ID)
	request, _ := http.NewRequest("DELETE", path, nil)
	response := httptest.NewRecorder()
	r.ServeHTTP(response, request)

	assert.Equal(t, http.StatusOK, response.Code)

	studentsRequest, _ := http.NewRequest("GET", "/students", nil)
	studentsResponse := httptest.NewRecorder()
	r.ServeHTTP(studentsResponse, studentsRequest)

	studentsResponseBody, _ := io.ReadAll(studentsResponse.Body)
	assert.Equal(t, `[]`, string(studentsResponseBody))
}

func TestEditStudent(t *testing.T) {
	database.ConnectDatabase()

	CreateStudent()

	r := RoutesSetup()
	r.PATCH("/students/:id", controllers.EditStudent)

	updatedStudent := models.Student{Name: "Student updated", CPF: "00987654321", RG: "987654321"}
	
	path := "/students/" + strconv.Itoa(ID)
	body, _ := json.Marshal(updatedStudent)

	request, _ := http.NewRequest("PATCH", path, bytes.NewBuffer(body))
	response := httptest.NewRecorder()
	r.ServeHTTP(response, request)

	var responseStudent models.Student
	json.Unmarshal(response.Body.Bytes(), &responseStudent)

	assert.Equal(t, http.StatusOK, response.Code)
	assert.Equal(t, updatedStudent.Name, responseStudent.Name)
	assert.Equal(t, updatedStudent.CPF, responseStudent.CPF)
	assert.Equal(t, updatedStudent.RG, responseStudent.RG)
}

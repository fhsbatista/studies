package controllers

import (
	"api-gin/database"
	"api-gin/models"
	"net/http"

	"github.com/gin-gonic/gin"
)

func Students(c *gin.Context) {
	var students []models.Student

	database.DB.Find(&students)

	c.JSON(200, students)
}

func Greetings(c *gin.Context) {
	name := c.Params.ByName("name")

	c.JSON(200, gin.H{
		"message": "Hello, " + name + ", how are u?",
	})
}

func Student(c *gin.Context) {
	id := c.Params.ByName("id")
	
	var student models.Student
	database.DB.First(&student, id)

	if student.ID == 0 {
		c.JSON(http.StatusNotFound, gin.H{
			"message": "Could not find student which id is " + id + ".",
		})
		return
	}

	c.JSON(http.StatusOK, &student)
}

func NewStudent(c *gin.Context) {
	var student models.Student

	if err := c.ShouldBindJSON(&student); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{
			"message": err.Error()})
		return
	}

	database.DB.Create(&student)

	c.JSON(http.StatusOK, &student)
}

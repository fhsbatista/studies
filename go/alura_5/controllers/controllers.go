package controllers

import (
	"api-gin/database"
	"api-gin/models"
	"net/http"

	"github.com/gin-gonic/gin"
)

func Students(c *gin.Context) {
	c.JSON(200, models.Students)
}

func Greetings(c *gin.Context) {
	name := c.Params.ByName("name")

	c.JSON(200, gin.H{
		"message": "Hello, " + name + ", how are u?",
	})
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

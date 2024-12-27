package controllers

import (
	"api-gin/models"

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

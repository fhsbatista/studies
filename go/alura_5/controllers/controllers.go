package controllers

import (
	"github.com/gin-gonic/gin"
)

func Students(c *gin.Context) {
	c.JSON(200, gin.H{
		"id":   "1",
		"name": "Fernando Batista",
	})
}

func Greetings(c *gin.Context) {
	name := c.Params.ByName("name")

	c.JSON(200, gin.H{
		"message": "Hello, " + name + ", how are u?",
	})
}

package main

import "github.com/gin-gonic/gin"

func Students(c *gin.Context) {
	c.JSON(200, gin.H{
		"id": "1",
		"name": "Fernando Batista",
	})
}

func main() {
	r := gin.Default()

	r.GET("/students", Students)

	r.Run(":8000")
}

package routes

import (
	"api-gin/controllers"

	"github.com/gin-gonic/gin"
)

func HandleRequests() {
	r := gin.Default()

	r.GET("/students", controllers.Students)
	r.GET("/greetings/:name", controllers.Greetings)

	r.Run(":8000")
}

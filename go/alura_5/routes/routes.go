package routes

import (
	"api-gin/controllers"

	"github.com/gin-gonic/gin"
)

func HandleRequests() {
	r := gin.Default()

	r.GET("/students", controllers.Students)
	r.GET("/greetings/:name", controllers.Greetings)
	r.GET("/students/:id", controllers.Student)
	r.GET("/students/cpf/:cpf", controllers.StudentByCPF)
	r.POST("/students", controllers.NewStudent)
	r.DELETE("/students/:id", controllers.DeleteStudent)
	r.PATCH("/students/:id", controllers.EditStudent)

	r.Run(":8000")
}

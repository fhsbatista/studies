package db

import (
	"database/sql"

	_ "github.com/lib/pq"
)

func ConnectDatabase() *sql.DB {
	conn := "user=postgres dbname=go_web password=default-password host=localhost sslmode=disable"
	db, err := sql.Open("postgres", conn)

	if err != nil {
		panic(err.Error())
	}

	return db
}

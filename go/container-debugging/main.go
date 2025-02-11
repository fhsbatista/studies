package main

import (
	"fmt"
	"net/http"
)

func hello(w http.ResponseWriter, r *http.Request) {
	fmt.Println("/hello has been HIT!!!")
	fmt.Fprintf(w, "hello!")
}

func main() {
	http.HandleFunc("/hello", hello)
	fmt.Println("Listening on :80")
	http.ListenAndServe(":80", nil)
}
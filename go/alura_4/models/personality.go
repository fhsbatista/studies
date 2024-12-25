package models

type Personality struct {
	Name        string `json:"name"`
	Description string `json:"description"`
}

var Personalities []Personality

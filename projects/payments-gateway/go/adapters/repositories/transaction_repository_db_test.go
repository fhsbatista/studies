package repositories

import (
	"os" 
	fixture "payments-gateway/adapters/repositories/fixtures"
	"payments-gateway/domain/entities"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestTransactionRepositoryDbInsert(t *testing.T) {
	migrationsDir := os.DirFS("fixtures/sql")
	db := fixture.Up(migrationsDir)
	defer fixture.Down(db, migrationsDir)

	repository := NewTransactionRepositoryDb(db)
	err := repository.Insert("1", "1", 11.0, entities.APPROVED, "")

	assert.Nil(t, err)
}
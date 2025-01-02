package factories

import (
	"database/sql"
	domain "payments-gateway/domain/repositories"
	factories "payments-gateway/adapters/repositories"
)

type RepositoryDatabaseFactory struct {
	DB *sql.DB
}

func NewRepositoryDatabaseFactory(db *sql.DB) *RepositoryDatabaseFactory {
	return &RepositoryDatabaseFactory{DB: db}
}

func (r *RepositoryDatabaseFactory) CreateTransactionRepository() domain.TransactionRepository {
	return factories.NewTransactionRepositoryDb(r.DB)
}
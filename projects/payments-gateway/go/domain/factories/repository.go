package factories

import "payments-gateway/domain/repositories"

type RepositoryFactory interface {
	CreateTransactionRepository() repositories.TransactionRepository 
}
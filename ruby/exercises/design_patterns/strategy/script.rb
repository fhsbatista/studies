require_relative "bank_account"
require_relative "transaction_fee_strategy"
require_relative "strategies/checking_account_fee_strategy"
require_relative "strategies/saving_account_fee_strategy"

checking_account = BankAccount.new(100, CheckingAccountFeeStrategy.new)
saving_account = BankAccount.new(100, SavingAccountFeeStrategy.new)
puts "Conta corrente: Taxa para a transação de 30 é: #{checking_account.calculate_transaction_fee(30)}"
puts "Conta poupança: Taxa para a transação de 30 é: #{saving_account.calculate_transaction_fee(30)}"

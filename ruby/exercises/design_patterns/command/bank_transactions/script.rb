require_relative "transaction_command"
require_relative "transactions_invoker"
require_relative "commands/transfer_command"
require_relative "commands/deposit_command"
require_relative "commands/withdraw_command"

transactions = TransactionsInvoker.new

transfer = TransferCommand.new("123-3", "234-4", 200)
deposit = DepositCommand.new("123-3", 200)
withdraw = WithdrawCommand.new("123-3", 100)

transactions.add_transaction(transfer)
transactions.add_transaction(deposit)
transactions.add_transaction(withdraw)

transactions.execute_transactions

class TransactionsInvoker
  def initialize
    @transactions = []
  end

  def add_transaction(transaction)
    @transactions << transaction
  end

  def execute_transactions
    @transactions.each { |transaction| transaction.execute}
  end
end
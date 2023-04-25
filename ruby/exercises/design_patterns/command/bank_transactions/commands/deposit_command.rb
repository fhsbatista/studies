class DepositCommand < TransactionCommand
  def initialize(account, amount)
    @account = account
    @amount = amount
  end

  def execute
    puts "Depositando #{@amount} na conta #{@account}"
  end
end

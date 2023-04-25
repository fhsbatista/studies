class WithdrawCommand < TransactionCommand
  def initialize(account, amount)
    @account = account
    @amount = amount
  end

  def execute
    puts "Sacando #{@amount} da conta #{@account}"
  end
end

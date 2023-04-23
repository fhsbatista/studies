class BankAccount
  def initialize(balance, fee_strategy)
    @balance = balance
    @fee_strategy = fee_strategy
  end

  def calculate_transaction_fee(value)
    @fee_strategy.calculate_fee(value)
  end
end

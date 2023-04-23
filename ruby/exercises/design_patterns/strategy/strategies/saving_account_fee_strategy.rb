class SavingAccountFeeStrategy < TransactionFeeStrategy
  def calculate_fee(value)
    value * 0.10
  end
end

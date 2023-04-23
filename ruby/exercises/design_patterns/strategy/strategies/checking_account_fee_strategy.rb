class CheckingAccountFeeStrategy < TransactionFeeStrategy
  def calculate_fee(value)
    value * 0.03
  end
end

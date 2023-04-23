class TransactionFeeStrategy
  def calculate_fee(transaction)
    raise NotImplementedError, "#{self.class} has not implemented method #{__method__}"
  end
end
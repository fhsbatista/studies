class PaymentGateway
  def pay
    raise NotImplementedError, "not implemented"
  end

  def refund
    raise NotImplementedError, "not implemented"
  end
end
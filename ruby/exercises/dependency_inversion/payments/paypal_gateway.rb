require_relative 'payment_gateway'

class PaypalGateway < PaymentGateway  
  def pay
    puts "Paying with Paypal!"
  end

  def refund
    puts "Refunding with Paypal!"
  end
end
require_relative 'payment_gateway'

class PagseguroGateway < PaymentGateway  
  def pay
    puts "Pagando com PagSeguro!"
  end

  def refund
    puts "Reembolsando com PagSeguro!"
  end
end
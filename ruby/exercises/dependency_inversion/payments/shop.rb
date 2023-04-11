require_relative 'pagseguro_gateway'
require_relative 'paypal_gateway'

gateway = PagseguroGateway.new()
gateway.pay()
gateway.refund()
gateway = PaypalGateway.new()
gateway.pay()
gateway.refund()
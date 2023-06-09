class OrderMailer < ApplicationMailer
  default from: 'Fernando <depot@store.com>'

  def received
    @order = order
    mail to: order.email, subject: 'Depot store order confirmation'
  end

  def shipped
    @order = order
    mail to: order.email, subject: 'Depot store order shipped'
  end
end

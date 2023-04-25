class TransferCommand < TransactionCommand
  def initialize(from, to, amount)
    @from = from
    @to = to
    @amount = amount
  end

  def execute 
    puts "Transferindo #{@amount} de #{@from} para #{@to}"
  end
end
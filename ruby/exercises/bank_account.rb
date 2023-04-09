class BankAccount
  def initialize(number, owner, balance)
    @number = number
    @owner = owner
    @balance = balance
  end

  def deposit(value)
    @balance = @balance + value
  end

  def withdraw(value)
    @balance = @balance - value
  end

  def info
    puts "-----------"
    puts "Info:"
    puts "Titular: #{@owner}"
    puts "Conta: #{@number}"
    puts "Saldo: #{@balance}"
    puts "-----------"
  end
end

conta = BankAccount.new("123", "Fernando Batista", 2)
conta.info
puts "Depositando 25"
conta.deposit 25
conta.info
puts "Sacando 20"
conta.withdraw 20
conta.info

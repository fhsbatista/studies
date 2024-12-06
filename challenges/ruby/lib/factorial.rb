class Factorial
  def self.calculate(value)
    #15! = 15 * 14!
    return 1 if value == 1
    value * calculate(value - 1)
  end
end

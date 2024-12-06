class SumN
  def self.sum(value)
    #value + sum(value - 1)
    return 0 if value.zero?
    value + sum(value - 1)
  end
end

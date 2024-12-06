class SumNumbers
  def self.sum(values)
    return 0 if values.empty?
    values.first + sum(values.drop(1))
  end
end

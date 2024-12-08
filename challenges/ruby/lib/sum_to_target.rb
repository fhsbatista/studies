class SumToTarget
  def self.find(values, target)
    values_map = {}
    values.each do |value|
      values_map[value] = true
    end

    sums = []

    values.each do |value|
      sum_to_target = target - value

      next if value == sum_to_target

      if values_map[sum_to_target] != nil
        sums << [value, sum_to_target]
        values_map[value] = nil
        values_map[sum_to_target] = nil
      end
    end

    sums
  end
end


class SmallestDifference
  def self.find(array1, array2)
    array2.sort!

    min_diff = {
      :pair => [array1.first, array2.first],
      :diff => (array1.first - array2.first).abs
    }

    array1.each do |value|
      diff = (value - array2.first).abs

      if diff < min_diff[:diff]
        min_diff[:pair] = [value, array2.first]
        min_diff[:diff] = diff
      end
    end

    return min_diff
  end
end

array1 = [1, 3, 15, 11, 2]
array2 = [23, 127,235, 19, 8]

p SmallestDifference.find(array1, array2)

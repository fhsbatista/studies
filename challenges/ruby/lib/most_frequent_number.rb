require 'byebug'

class MostFrequentNumber
  def self.find(numbers)
    map = {}
    frequent = numbers.first
    numbers.each do |number|
      map[number] ||= 0
      map[number] += 1
      frequent = number if (map[number] > map[frequent]) || (map[number] == map[frequent] && number < frequent)
    end

    frequent
  end
end

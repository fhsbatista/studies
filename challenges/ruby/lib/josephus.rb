require 'byebug'
class Josephus
  def self.find_survivor(strategy, men_number, count)
    case strategy
    when :recursive
      return recursive(men_number, count)
    when :queue
      return queue(men_number, count)
    end
  end

  private

  def self.recursive(men_number, count)
    return 1 if men_number == 1

    (recursive(men_number - 1, count) + count - 1) % men_number + 1
  end

  def self.queue(men_number, count)
    men = (1..men_number).to_a

    counter = 1

    while men.length > 1 do
      number = men.first
      men.delete_at(0)
      if counter != count
        men.push(number)
        counter += 1
      else
        counter = 1
      end
    end

    men.first
  end
end

module Gender
  POSSIBLE_VALUES = [:romance, :fiction, :horror]

  def self.values
    POSSIBLE_VALUES
  end
end

class Book
  attr_reader :gender

  def initialize(gender)
    raise ArgumentError, "Invalid gender" unless Gender.values.include?(gender)
    @gender = gender
  end
end

book = Book.new(:horror)
puts book.gender

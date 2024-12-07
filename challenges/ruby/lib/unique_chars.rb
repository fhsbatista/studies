require 'set'

class UniqueChars
  def self.count(string)
    set = Set.new
    string.chars.each do |char|
      set.add char unless special_char? char
    end

    set.count
  end

  private

    def self.special_char?(char)
      [',', ' ', '!'].include? char
    end
end

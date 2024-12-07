class Palindrome
  # O(n * m), n = words size, m = longest word length
  def self.find(words)
    palindromes = []

    words.each do |word|
      palindromes << word if palindrome?(word)
    end

    palindromes
  end

  private

  def self.palindrome?(word)
    word == reverse(word)
  end

  def self.reverse(word)
    reverse = ''

    word.chars.each do |char|
      reverse = "#{char}#{reverse}"
    end

    reverse
  end
end

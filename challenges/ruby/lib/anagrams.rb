class Anagrams
  def self.find(words)
    anagrams = []

    words.each do |word|
      if anagrams.flatten.include? word
        next
      end

      included = false
      anagrams.each do |words|
        if anagram_in?(words, word)
          words << word
          included = true
          break
        end
      end

      if !included
        anagrams << [word]
      end
    end

    anagrams
  end

  private

  def self.anagram_in?(words, word)
    words.any? { |e| anagram?(word, e)}
  end

  def self.anagram?(word, other_word)
    return false if word == other_word
    return false if word.length != other_word.length
    return false if word.chars - other_word.chars != []
    return false if other_word.chars - word.chars != []

    true
  end
end

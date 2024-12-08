class FindAnagramInOtherList
  def self.find(array1, array2)
    array2_signatures = {}

    array2.each do |word|
      array2_signatures[signature(word)] = true
    end

    anagrams = []

    array1.each do |word|
      anagrams << word if array2_signatures[signature(word)] != nil
    end

    anagrams
  end

  private

  def self.signature(word)
    table = {'a' => 2, 'b' => 3, 'c' => 5, 'd' => 7, 'e' => 11, 'f' => 13, 'g' => 17, 'h' => 19, 'i' => 23, 'j' => 29, 'k' => 31,
    'l' => 37, 'm' => 41, 'n' => 43, 'o' => 47, 'p' => 53, 'q' => 59, 'r' => 61, 's' => 67, 't' => 71, 'u' => 73, 'v' => 79,
    'w' => 83, 'x' => 89, 'y' => 97, 'z' => 101}

    word.chars.reduce(0) { |acumulator, char| table[char] + acumulator }
  end
end

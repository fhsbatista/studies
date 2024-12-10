class WordFrequency
  def initialize(words)
    @index = {}

    words.each do |word|
      @index[word] ||= 0
      @index[word] += 1
    end
  end

  def count(word)
    @index[word]
  end
end

words = [
  "apple", "melon", "grape", "pomegranate", "pomegranate",
  "raspberry", "melon", "cherry", "strawberry", "pomegranate",
  "raspberry", "peach", "melon", "watermelon", "blueberry",
  "blackberry", "raspberry", "melon", "nectarine", "pomegranate"
]

frequency = WordFrequency.new(words)
p frequency.count('blueberry')

puts "Digite uma frase para inverter as palavras:"
phrase = gets.to_s
words = phrase.split
reverse_words = []
words.each do |word|
  reverse_words.push(word.reverse, ' ')
end
reverse_phrase = reverse_words.join
puts reverse_phrase

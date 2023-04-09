puts "Digite o raio do círculo"
radius = gets.to_f
area = Math::PI * radius * radius
puts "A área do círculo é: #{area.round(2)}"
require_relative "password_validation"
require_relative "composite_validation"
require_relative "validations/length_validation"
require_relative "validations/uppercase_validation"
require_relative "validations/special_char_validation"
require_relative "validations/number_char_validation"

easyPasswordValidation = CompositeValidation.new([
  LengthValidation.new(3),
])
mediumPasswordValidation = CompositeValidation.new([
  LengthValidation.new(8),
  UppercaseValidation.new,
])
impossiblePasswordValidation = CompositeValidation.new([
  LengthValidation.new(6),
  UppercaseValidation.new,
  SpecialCharValidation.new,
  NumberCharValidation.new,
])

puts "Digite uma senha"
password = gets.chomp

is_easy = easyPasswordValidation.validate(password)
is_medium = mediumPasswordValidation.validate(password)
is_impossible = impossiblePasswordValidation.validate(password)

if is_impossible
  puts "A senha é impossível"
elsif is_medium
  puts "A senha é média"
elsif is_easy
  puts "A senha é fácil"
else
  puts "Senha não válida"
end

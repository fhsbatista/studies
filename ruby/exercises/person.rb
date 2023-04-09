class Person
  def initialize(name, age, gender)
    @name = name
    @age = age
    @gender = gender
  end

  def speak
    puts "Oi, meu nome é #{@name}"
  end

  def info
    puts "Minhas informações. Nome: #{@name}, Idade: #{@age}, Sexo: #{@gender}"
  end

  def age
    @age = @age + 1
  end
end

person = Person.new("Fernando", 27, "masculino")
person.speak
person.info
person.age
person.info

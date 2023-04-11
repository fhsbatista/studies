def divide(num1, num2)
  begin 
    resultado = num1 / num2
  rescue ZeroDivisionError => e
    puts "Erro: #{e.message}"
  else
    puts "Resultado: #{resultado}"
  end
end

divide(2,3)
divide(2,0)
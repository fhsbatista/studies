class Service
  def execute(number)
    if number <=5
      return :success
    else
      return :error
    end
  end
end
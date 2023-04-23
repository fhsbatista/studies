class LengthValidation < PasswordValidation
  def initialize(min_length)
    @min_length = min_length
  end

  def validate(password)
    password.length >= @min_length
  end
end
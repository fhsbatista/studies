class NumberCharValidation < PasswordValidation
  def validate(password)
    password.match(/\d+/)
  end
end
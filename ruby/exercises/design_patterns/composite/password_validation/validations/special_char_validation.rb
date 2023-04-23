class SpecialCharValidation < PasswordValidation
  def validate(password)
    password.match(/[\W_]/)
  end
end
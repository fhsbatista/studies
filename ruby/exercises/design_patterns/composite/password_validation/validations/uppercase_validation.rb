class UppercaseValidation < PasswordValidation
  def validate(password)
    password.match(/[[:upper:]]/)
  end
end
class CompositeValidation < PasswordValidation
  def initialize(validations)
    @validations = validations
  end

  def validate(password)
    @validations.all? { |validation| validation.validate(password) }
  end
end

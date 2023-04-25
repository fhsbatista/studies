class Decorator
  def initialize(service)
    @service = service
  end

  def execute(number)
    service_result = @service.execute(number)
    if (service_result == :error)
      puts "Service falhou para o number #{number}"
    end
  end
end

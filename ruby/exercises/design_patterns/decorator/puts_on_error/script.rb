require_relative "service"
require_relative "decorator"

service = Service.new
decorator = ServiceWithPutsOnErrorDecorator.new(service)
decorator.execute(4)
decorator.execute(5)
decorator.execute(6)
decorator.execute(7)

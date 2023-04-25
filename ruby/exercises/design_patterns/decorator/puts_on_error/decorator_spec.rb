require_relative "service"
require_relative "decorator"

RSpec.describe Decorator do
  describe "#execute" do
    it "Should puts if service returns :error" do
      service = Service.new
      decorator = Decorator.new(service)
      expect { decorator.execute(6) }.to output("Service falhou para o number 6\n").to_stdout
    end
  end
end

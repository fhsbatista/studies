require_relative "service"

RSpec.describe Service do
  describe "#execute" do
    it "returns :error for correct numbers" do
      service = Service.new
      expect(service.execute(6)).to eq(:error)
      expect(service.execute(7)).to eq(:error)
      expect(service.execute(8)).to eq(:error)
    end
  end
end

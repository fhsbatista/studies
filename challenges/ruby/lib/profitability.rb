class Property
  attr_reader :name, :monthly_income, :acquisition_value

  def initialize(name:, monthly_income:, acquisition_value:)
    @name = name
    @monthly_income = monthly_income
    @acquisition_value = acquisition_value
  end

  def profitability
    ((@monthly_income * 12 / @acquisition_value) * 100).round(2)
  end
end

class Profitability
  def self.calc(properties)
    profitabilities = properties.map do |p|
      {
        property: p.name,
        profitability: p.profitability
      }
    end

    profitabilities.sort_by { |p| -p[:profitability] }
  end
end

properties = []
properties << Property.new(name: "Apto 1", monthly_income: 2000.0, acquisition_value: 250000.0)
properties << Property.new(name: "Apto 2", monthly_income: 3000.0, acquisition_value: 300000.0)
properties << Property.new(name: "Apto 3", monthly_income: 2000.0, acquisition_value: 600000.0)
properties << Property.new(name: "Apto 4", monthly_income: 2100.0, acquisition_value: 500000.0)
properties << Property.new(name: "Apto 5", monthly_income: 4000.0, acquisition_value: 230000.0)
properties << Property.new(name: "Apto 6", monthly_income: 1200.0, acquisition_value: 450000.0)
properties << Property.new(name: "Apto 7", monthly_income: 800.0, acquisition_value: 120000.0)
properties << Property.new(name: "Apto 8", monthly_income: 2000.0, acquisition_value: 250000.0)

puts Profitability.calc(properties)

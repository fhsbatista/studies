require 'spec_helper'
require 'city_routes'

RSpec.describe CityRoutes do
  it "returns correct routes" do
    input = [
      ['A', 'B'],
      ['B', 'C'],
      ['C', 'D'],
      ['D', 'E'],
      ['E', 'F']
    ]

    expected = 'A -> B -> C -> D -> E -> F'

    expect(CityRoutes.calculate(input)).to eq(expected)
  end

  it "returns correct first origin" do
    input = [
      ['A', 'B'],
      ['B', 'C'],
      ['C', 'D'],
      ['D', 'E'],
      ['E', 'F']
    ]

    expected = 'A'

    expect(CityRoutes.find_first_origin(input)).to eq(expected)
  end
end

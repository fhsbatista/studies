require 'spec_helper'
require 'sum_numbers'

RSpec.describe SumNumbers do
  it "returns value" do
    input = [1, 2, 3, 4, 5]

    expect(SumNumbers.sum(input)).to eq(15)
  end
end

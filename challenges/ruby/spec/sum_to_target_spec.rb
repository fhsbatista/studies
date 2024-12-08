require 'spec_helper'
require 'sum_to_target'

RSpec.describe SumToTarget do
  input = [1, 2, 3, 7, 5, 8, 9]
  expected = [[1, 9], [2, 8], [3, 7]]
  it { expect(SumToTarget.find(input, 10)).to eq(expected) }
end

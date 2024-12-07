require 'spec_helper'
require 'intersection'

RSpec.describe Intersection do
  input1 = [1, 2, 3, 4, 5]
  input2 = [4, 5, 6, 7, 8]
  expected = [4, 5]
  it { expect(Intersection.find(input1, input2)).to eq(expected) }
end

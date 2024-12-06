require 'spec_helper'
require 'sum_n'

RSpec.describe SumN do
  it { expect(SumN.sum(5)).to eq(15) }
end

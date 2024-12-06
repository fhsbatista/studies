require 'spec_helper'
require 'factorial'

RSpec.describe Factorial do
  it { expect(Factorial.calculate(5)).to eq(120) }
end

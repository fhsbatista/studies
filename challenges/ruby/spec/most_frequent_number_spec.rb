require 'spec_helper'
require 'most_frequent_number'

RSpec.describe MostFrequentNumber do
  it { expect(MostFrequentNumber.find([1, 3, 2, 1, 2, 4, 2, 1])).to eq(1) }
  it { expect(MostFrequentNumber.find([2, 1, 2, 3, 2, 4, 2])).to eq(2) }
end

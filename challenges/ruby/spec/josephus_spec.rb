require 'spec_helper'
require 'josephus'

RSpec.describe Josephus do
  it { expect(Josephus.find_survivor(:recursive, 15, 3)).to eq(5) }
  it { expect(Josephus.find_survivor(:queue, 15, 3)).to eq(5) }
end

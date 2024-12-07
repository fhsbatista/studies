require 'spec_helper'
require 'unique_chars'

RSpec.describe UniqueChars do
  it { expect(UniqueChars.count('Hello, World!')).to eq(7) }
end

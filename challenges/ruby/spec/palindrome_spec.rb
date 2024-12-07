require 'spec_helper'
require 'palindrome'

RSpec.describe Palindrome do
  input = ["abba", "otto", "dog", "cat", "dood"]
  expected = ['abba', 'otto', 'dood']
  it { expect(Palindrome.find(input)).to eq(expected) }
end

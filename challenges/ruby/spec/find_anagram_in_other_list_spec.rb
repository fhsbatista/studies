require 'spec_helper'
require 'find_anagram_in_other_list'

RSpec.describe FindAnagramInOtherList do
  input1 = ["abc", "def", "bac"]
  input2 = ["cba", "wee", "ddo"]
  expected = ["abc", "bac"]
  it { expect(FindAnagramInOtherList.find(input1, input2)).to eq(expected) }
end

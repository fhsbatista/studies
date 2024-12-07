require 'spec_helper'
require 'anagrams'

RSpec.describe Anagrams do
  input = ["listen", "silent", "enlist", "rat", "tar", "art", "hello"]
  expected = [["listen", "silent", "enlist"], ["rat", "tar", "art"], ["hello"]]
  it { expect(Anagrams.find(input)).to eq(expected) }
end

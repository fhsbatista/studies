require 'spec_helper'
require 'binary_search'

RSpec.describe BinarySearch do
  describe "find value" do
    fit "find correct index" do
      array = [1, 2, 5, 7, 8, 10, 11, 15, 18]

      expect(BinarySearch.search(array, 15)).to eq(7)
      expect(BinarySearch.search(array, 1)).to eq(0)
      expect(BinarySearch.search(array, 7)).to eq(3)
      expect(BinarySearch.search(array, 100)).to eq(-1)
    end
  end
end

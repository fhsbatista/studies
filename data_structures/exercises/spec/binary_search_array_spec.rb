require 'spec_helper'
require 'binary_search_array'

RSpec.describe BinarySearchArray do
  describe "find value" do
    it "find correct index" do
      array = [1, 2, 5, 7, 8, 10, 11, 15, 18]

      expect(BinarySearchArray.search(array, 15)).to eq(7)
      expect(BinarySearchArray.search(array, 1)).to eq(0)
      expect(BinarySearchArray.search(array, 7)).to eq(3)
      expect(BinarySearchArray.search(array, 100)).to eq(-1)
    end
  end
end

require 'spec_helper'
require 'binary_tree'

RSpec.describe BinaryTree do
  describe "operations" do
    it "add on tree correctly" do
      tree = BinaryTree.new
      tree.insert(10)
      tree.insert(19)
      tree.insert(11)
      tree.insert(24)
      tree.insert(100)
      tree.insert(8)
      tree.insert(12)

      expect(tree.root.value).to eq(10)
      expect(tree.root.left.value).to eq(19)
      expect(tree.root.right.value).to eq(11)
      expect(tree.root.left.left.value).to eq(24)
      expect(tree.root.left.right.value).to eq(100)
      expect(tree.root.right.left.value).to eq(8)
      expect(tree.root.right.right.value).to eq(12)
    end

    fit "finds node" do
      tree = BinaryTree.new
      tree.insert(10)
      tree.insert(19)
      tree.insert(11)
      tree.insert(24)
      tree.insert(100)
      tree.insert(8)
      tree.insert(12)

      expect(tree.search(24)).not_to be_nil
      expect(tree.search(100)).not_to be_nil
      expect(tree.search(10)).not_to be_nil
      expect(tree.search(1000)).to be_nil
      expect(tree.search(1)).to be_nil
    end
  end
end

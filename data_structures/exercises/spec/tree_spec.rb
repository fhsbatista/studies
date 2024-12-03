require 'spec_helper'
require 'tree'

RSpec.describe Tree do
  describe "operations" do
    it "add on tree correctly" do
      tree = Tree.new 10
      tree.root.add_child 11
      tree.root.add_child 12
      tree.root.add_child 13

    end

    context "listing" do
      it "list vertically" do
=begin
         10
      /  |  \
     11  12 13
     |   |
     21  50
     |
     22
=end
        tree = Tree.new 10
        tree.root.add_child 11
        tree.root.add_child 12
        tree.root.add_child 13
        tree.root.children[0].add_child 21
        tree.root.children[1].add_child 50
        tree.root.children.first.children.first.add_child 22
        expect(tree.list_depth_vertical).to eq("10, 11, 21, 22, 12, 50, 13")
      end

      it "list horizontally" do
=begin
         10
      /  |  \
     11  12 13
     |   |
     21  50
     |
     22
=end
        tree = Tree.new 10
        tree.root.add_child 11
        tree.root.add_child 12
        tree.root.add_child 13
        tree.root.children[0].add_child 21
        tree.root.children[1].add_child 50
        tree.root.children.first.children.first.add_child 22
        expect(tree.list_breath_horizontal).to eq("10, 11, 12, 13, 21, 50, 22")
      end
    end


  end
end

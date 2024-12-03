require 'spec_helper'
require 'linked_list'

RSpec.describe LinkedList do

  describe "initialization" do
    let(:list) { LinkedList.new }
    it { expect(list.head).to be_nil }
  end

  describe "operations" do
    let(:list) { LinkedList.new }
    let(:element) { "elemented_added" }

    context "adding at the beginning" do
      before do
        list.add(element)
      end

      it { expect(list.head.value).to be(element) }
      it { expect(list.head.next_node).to be_nil }
    end

    context "get at index" do
      context "when index does not exist" do
        before do
          list.add("index 0")
          list.add("index 1")
          list.add("index 2")
          list.add("index 3")
        end

        it { expect(list.get_at(5)).to be_nil }
      end
      context "when index exists" do
        before do
          list.add("index 0")
          list.add("index 1")
          list.add("index 2")
          list.add("index 3")
        end

        it { expect(list.get_at(2)).to eq("index 1") }
      end
    end
  end
end

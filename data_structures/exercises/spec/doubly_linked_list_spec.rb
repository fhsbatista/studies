require 'spec_helper'
require 'doubly_linked_list'
require 'byebug'

RSpec.describe DoublyLinkedList do

  describe "initialization" do
    let(:list) { DoublyLinkedList.new }
    it { expect(list.head).to be_nil }
  end

  describe "operations" do
    let(:list) { DoublyLinkedList.new }
    let(:element) { "elemented_added" }

    context "adding at the beginning" do
      let(:last_element) { "last_element" }

      before do
        list.add_last(last_element)
        list.add_first(element)
      end

      it { expect(list.head.value).to be(element) }
      it { expect(list.tail.value).to be(last_element) }
      it { expect(list.head.previous_node).to be_nil }
      it { expect(list.head.next_node.value).to eq(last_element) }
    end

    context "adding at the end" do
      let(:first_element) { "first element" }
      before do
        list.add_first(first_element)
        list.add_last(element)
      end

      it { expect(list.head.value).to be(first_element) }
      it { expect(list.tail.value).to be(element) }
      it { expect(list.tail.previous_node.value).to eq(first_element) }
      it { expect(list.tail.next_node).to be_nil }
    end

    context "get at index" do
      context "when index does not exist" do
        before do
          list.add_last("index 1")
          list.add_last("index 2")
          list.add_last("index 3")
          list.add_first("index 0")
        end

        it { expect(list.get_at(5)).to be_nil }
      end

      context "when index exists" do
        before do
          list.add_last("index 1")
          list.add_last("index 2")
          list.add_last("index 3")
          list.add_first("index 0")
        end

        it { expect(list.get_at(0)).to eq("index 0") }
        it { expect(list.get_at(3)).to eq("index 3") }
      end
    end
  end
end

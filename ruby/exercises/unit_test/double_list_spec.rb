require "rspec"
require_relative "double_list"

describe "double_list" do
  it "does not modify the original list" do
    original_list = [1, 2, 1, 2, 3]
    doubled_list = DoubleList.new.double_list(original_list)
    expect(original_list).to eq([1, 2, 1, 2, 3])
  end

  it "returns list with double values correctly" do
    original_list = [1, 2, 1, 2, 3]
    doubled_list = DoubleList.new.double_list(original_list)
    expect(doubled_list).to eq(doubled_list)
  end
end

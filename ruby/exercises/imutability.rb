def double_list(list)
  list.map { |n| n * 2 }
end

list = ["teste", 1, 2, 3, 3, 5, 10]
doubled_list = double_list(list)
puts doubled_list.inspect

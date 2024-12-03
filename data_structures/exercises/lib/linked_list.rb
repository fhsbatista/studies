class Node
  attr_accessor :value, :next_node

  def initialize(value)
    @value = value
    @next_node = nil
  end
end

class LinkedList
  attr_reader :head

  def initialize
    @head = nil
  end

  def add(value)
    node = Node.new(value)
    node.next_node = @head
    @head = node
  end

  def get_at(index)
    current_node = @head
    current_index = 0

    while current_node
      return nil if current_node.next_node.nil?
      return current_node.value if current_index == index

      current_node = current_node.next_node
      current_index = current_index + 1
    end
  end
end

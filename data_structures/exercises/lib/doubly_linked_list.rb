class Node
  attr_accessor :value, :next_node, :previous_node

  def initialize(value)
    @value = value
    @previous_node = nil
    @next_node = nil
  end
end

class DoublyLinkedList
  attr_reader :head, :tail

  def initialize
    @head = nil
    @tail = nil
  end

  def add_first(value)
    node = Node.new(value)

    if @head.nil?
      @head = node
      @tail = node
    else
      node.next_node = @head
      @head.previous_node = node
      @head = node
    end
  end

  def add_last(value)
    node = Node.new(value)

    if @tail.nil?
      @head = node
      @tail = node
    else
      node.previous_node = @tail
      @tail.next_node = node
      @tail = node
    end
  end

  def get_at(index)
    current_node = @head
    current_index = 0

    while current_node
      return current_node.value if current_index == index

      current_node = current_node.next_node
      current_index = current_index + 1
    end

    nil
  end
end

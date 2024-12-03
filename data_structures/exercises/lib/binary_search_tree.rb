class TreeNode
  attr_accessor :value, :left, :right

  def initialize(value)
    @value = value
    @left = nil
    @right = nil
  end
end

class BinarySearchTree
  attr_reader :root
  def initialize
    @root = nil
  end

  def insert(value)
    @root = insert_node(@root, value)
  end

  def search(value)
    search_node(@root, value)
  end

  private

  def search_node(node, value)
    return nil if node.nil?

    return node if node.value == value

    if value < node.value
      search_node(node.left, value)
    else
      search_node(node.right, value)
    end
  end

  def insert_node(node, value)
    return TreeNode.new(value) if node.nil?
    return if node.value == value

    if value < node.value
      node.left = insert_node(node.left, value)
    else
      node.right = insert_node(node.right, value)
    end

    node
  end
end

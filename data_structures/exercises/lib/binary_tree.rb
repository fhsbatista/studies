class TreeNode
  attr_accessor :value, :left, :right

  def initialize(value)
    @value = value
    @left = nil
    @right = nil
  end
end

class BinaryTree
  attr_reader :root
  def initialize
    @root = nil
  end

  def insert(value)
    new_node = TreeNode.new(value)

    if @root.nil?
      @root = new_node
    else
      queue = [@root]
      while !queue.empty?
        node = queue.shift

        if node.left.nil?
          node.left = new_node
          return
        else
          queue. << node.left
        end

        if node.right.nil?
          node.right = new_node
          return
        else
          queue << node.right
        end
      end
    end
  end

  def search(value)
    search_node(@root, value)
  end

  private

  def search_node(node, value)
    return nil if node.nil?
    return node if node.value == value

    left = search_node(node.left, value)
    return left if left

    right = search_node(node.right, value)
    return right if right

    nil
  end
end

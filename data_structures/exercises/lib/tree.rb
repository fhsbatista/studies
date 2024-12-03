class TreeNode
  attr_accessor :value, :children

  def initialize(value)
    @value = value
    @children = []
  end

  def add_child(value)
    @children << TreeNode.new(value)
  end
end

class Tree
  attr_reader :root

  def initialize(root_value)
    @root = TreeNode.new(root_value)
  end

  def list_depth_vertical(node = @root)
    scanned_nodes = scan_vertical(node)

    scanned_nodes.map(&:value).join(', ')
  end

  def list_breath_horizontal(node = @root)
    scanned_nodes = scan_horizontal(node)

    scanned_nodes.map(&:value).join(', ')
  end

  private

  def scan_vertical(node = @root, scanned_nodes = [])
    return if node.nil?

    scanned_nodes << node

    node.children.each do |child|
      scan_vertical(child, scanned_nodes)
    end

    scanned_nodes
  end

  def scan_horizontal(node = @root, scanned_nodes = [])
    return if node.nil?

    queue = [@root]
    scanned_nodes = []

    while !queue.empty?
      node = queue.shift
      scanned_nodes << node
      queue.concat(node.children)
    end

    scanned_nodes
  end

end

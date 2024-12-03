class Graph
  def initialize
    @graph = {}
  end

  def add_vertex(vertex)
    @graph[vertex] = [] unless @graph.key?(vertex)
  end

  def add_edge(vertex1, vertex2)
    add_vertex(vertex1)
    add_vertex(vertex2)
    @graph[vertex1] << vertex2
    @graph[vertex2] << vertex1
  end

  def display
    @graph.each do |vertex, edges|
      puts "#{vertex} -> #{edges.join(', ')}"
    end
  end

  def vertexes_connected?(vertex1, vertex2)
    @graph[vertex1].include? vertex2
  end
end

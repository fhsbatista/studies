require 'spec_helper'
require 'graph'

RSpec.describe Graph do
  describe "operations" do
    it "vertexes are connected correctly" do
      graph = Graph.new
      graph.add_edge(:monte_aprazivel, :sj_rio_preto)
      graph.add_edge(:monte_aprazivel, :tanabi)
      graph.add_edge(:tanabi, :sj_rio_preto)
      graph.add_edge(:sj_rio_preto, :catanduva)

      expect(graph.vertexes_connected?(:tanabi, :monte_aprazivel)).to be_truthy
      expect(graph.vertexes_connected?(:tanabi, :sj_rio_preto)).to be_truthy
      expect(graph.vertexes_connected?(:monte_aprazivel, :sj_rio_preto)).to be_truthy
      expect(graph.vertexes_connected?(:sj_rio_preto, :catanduva)).to be_truthy
      expect(graph.vertexes_connected?(:tanabi, :catanduva)).to be_falsy
      expect(graph.vertexes_connected?(:monte_aprazivel, :catanduva)).to be_falsy
    end
  end
end

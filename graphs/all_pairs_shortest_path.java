class DiGraph {

  Map<Integer, ArrayList<HashMap<Integer, Integer>>> edges;

  /**
   * Constructs an empty graph
   */
  public DiGraph() {
    edges = new HashMap<Integer, ArrayList<HashMap<Integer, Integer>>>();
  }

  /**
   * Populate Graph with List of Edges
   * @param Map edges - The Adjacency list representation of the graph
   */
  public void loadGraph(Map<Integer, ArrayList<HashMap<Integer, Integer>>> edges) {
    for(int vertex: edges.keySet()) {
      this.addVertex(vertex);
      for(Map<Integer, Integer> dest_wgt_pair : edges.get(vertex)) {
        Integer destValue = (Integer)dest_wgt_pair.keySet().toArray()[0];
        int dest = destValue.intValue();
        int wgt = dest_wgt_pair.get(dest);
        this.addEdge(vertex, dest, wgt);
      }
    }
  }
}
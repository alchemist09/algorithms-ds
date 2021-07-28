class Graph {

  /**
   * Constructs an empty graph
   */
  public Graph() {
    Map<Integer, ArrayList<Integer>> edges;
  }

  /**
   * Constructs a graph from an edge list
   * @param edgeList
   */
  public Graph(Map<Integer, ArrayList<Integer>> edgeList) {
    edges = new HashMap<Integer, ArrayList<Integer>>();
    this.loadGraph(edgeList);
  }
}
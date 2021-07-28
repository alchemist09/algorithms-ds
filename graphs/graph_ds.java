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

  /**
   * Add a vertex
   */
  public void addVertex(int vertex) {
    if(!edges.containsKey(vertex)) {
      ArrayList<Integer> adjacencyList = new ArrayList<Integer>();
      edges.put(vertex, adjacencyList);
    }
  }

  /**
   * Add an edge
   */
  public void addEdge(int src, int dest) {
    if(!edges.containsKey(src)) {
      edges.put(src, new ArrayList<Integer>());
    }
    if(!edges.containsKey(dest)) {
      edges.put(dest, new ArrayList<Integer>());
    }

    List<Integer> src_adjacency_list = edges.get(src);
    List<Integer> dest_adjacency_list = edges.get(dest);

    if(!src_adjacency_list.contains(dest)) {
      src_adjacency_list.add(dest);
    }
    
    if(!dest_adjacency_list.contains(src)) {
      dest_adjacency_list.add(src);
    }
  }
}
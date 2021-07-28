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

  /**
   * Determines whether an edge exists
   */ 
  public boolean isEdge(int src, int dest) {
    if(!edges.containsKey(src)) {
      return false;
    }

    if(!edges.containsKey(dest)) {
      return false;
    }
    
    List<Integer> adjacencList = edges.get(src);
    return adjacencList.contains(dest);
  }

  /**
   * Populate Graph with List of Edges
   */
  public void loadGraph(Map<Integer, ArrayList<Integer>> edges) {
    for(int vertex: edges.keySet()) {
      this.addVertex(vertex);
      for(int neighbour: edges.get(vertex)) {
        this.addEdge(vertex, neighbour);
      }
    }
  }

  /**
   * Checks whether graph is empty, i.e has no vertices
   */
  public boolean isEmpty() {
    return edges.size() == 0 ? true : false;
  }

  /**
   * Fetch all the edges of the graph
   */
  public List<Integer> getEdges(int u) {
    return edges.get(u);
  }
}




class DepthFirstTraversal {
  // Coloring constants
  private static final int White = 0;
  private static final int Gray  = 1;
  private static final int Black = 2;

  Graph graph;
  Map<Integer, Integer> pred;
  Map<Integer, Integer> color;
  int start;

  public void dfs_visit(int u) {
    // recursive traversal of graph using DFS
    color.put(u, Gray);
    // System.out.println("Color Map: " + color);
    for(int v : graph.edges.get(u)) {
      // System.out.println("Vertex: " + v);
      if(color.get(v) == White) {
        pred.put(v, u);
        this.dfs_visit(v);
      }
    }
    int temp = color.replace(u, Black);
    // int temp = color.get(u);
    // System.out.println(String.format("Vertex %s, Color %s", u, temp ));
  }
}
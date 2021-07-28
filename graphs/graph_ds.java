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

  /**
   * Constructor
   * @param g Graph - The graph to traverse in depth-first manner
   * @param s vertex -  An integer denoting the start vertex of the traversal
   */
  public DepthFirstTraversal(Graph g, int s) {
    // Initialize a DFS traversal of the graph
    graph = g;
    start = s;
    pred = new HashMap<Integer, Integer>();
    color = new HashMap<Integer, Integer>();

    // Set initial color of all vertices before beginning traversal
    // Set the predecessor of all vertices as null

    for(int v : graph.edges.keySet()) {
      color.put(v, White);
      pred.put(v, null);
    }

    // Do the DFS 
    this.dfs_visit(s);
  }

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

  /**
   * Prints the order in which the vertices were traversed
   * @param v vertex
   * @return order otf traversal
   */
  public List<Integer> solution(int v) {
    // vertex not within graph
    if(!graph.edges.containsKey(v)) {
      return null;
    }

    // Disconnected graph
    if(pred.get(v) == null) {
      return null;
    }

    List<Integer> path = new ArrayList<Integer>();
    path.add(v);
    while(v != start) {
      v = pred.get(v);
      path.add(0, v);
    }
    return path;
  }
}
class DiGraph {

  Map<Integer, ArrayList<HashMap<Integer, Integer>>> edges;

  /**
   * Constructs an empty graph
   */
  public DiGraph() {
    edges = new HashMap<Integer, ArrayList<HashMap<Integer, Integer>>>();
  }

  /**
   * Initializes a graph from an adjacency list 
   */
  public DiGraph(Map<Integer, ArrayList<HashMap<Integer, Integer>>> edgeList) {
    edges = new HashMap<Integer, ArrayList<HashMap<Integer, Integer>>>();
    this.loadGraph(edgeList);
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

  /**
   * Add a vertex
   * @param int vertex - A vertex on the graph
   */
  public void addVertex(int vertex) {
    if(!edges.containsKey(vertex)) {
      ArrayList<HashMap<Integer, Integer>> adjacencyList = new ArrayList<HashMap<Integer, Integer>>();
      edges.put(vertex, adjacencyList);
    }
  }

  /**
   * Add an edge
   * @param int src - The source vertex
   * @param int dest - The destination vertex
   * @param int wgt - The weight associated with the edge
   */
  public void addEdge(int src, int dest, int wgt) {
    // case where the source vertex doesn't exist within graph
    if(!edges.containsKey(src)) {
      // build the mapping of destination and weight
      HashMap<Integer, Integer> dst = new HashMap<Integer, Integer>(1);
      dst.put(dest, wgt);
      edges.put(src, new ArrayList<HashMap<Integer, Integer>>(Arrays.asList(dst)));
    } else {
      // case where the source vertex already exists, we just add a directed
      // edge to the destination vertex
      HashMap<Integer, Integer> dst = new HashMap<Integer, Integer>(1);
      dst.put(dest, wgt);
      List<HashMap<Integer, Integer>> adjacencyList = edges.get(src);
      adjacencyList.add(dst);
    }
  }

  /**
   * Determines whether an edge exists btwn a source and destination
   * @param int src - The source vertex
   * @param int dest - The destination vertex
   * @return boolean - Returns true if an edge exists between two vertices, 
                       false otheriwse
   */ 
  public boolean isEdge(int src, int dest) {
    if(!edges.containsKey(src) || !edges.containsKey(dest)) {
      return false;
    }
    
    List<HashMap<Integer, Integer>> adjacencyList = edges.get(src);
    for(Map<Integer, Integer> item : adjacencyList) {
      Integer destValue = (Integer)item.keySet().toArray()[0];
      if(destValue.intValue() == dest) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get the weight associated with an edge 
   * @param src The source vertex of the edge
   * @param dest The destination vertex of the edge
   * @return int The weight associated with the edge
   */
  public int getEdgeWeight(int src, int dest) {
    if(!this.isEdge(src, dest)) {
      return Integer.MAX_VALUE;
    }
    ArrayList<HashMap<Integer, Integer>> adjList = edges.get(src);
    for(HashMap<Integer, Integer> destVertex : adjList) {
      if(destVertex.containsKey(dest)) {
        Integer wgt = destVertex.get(dest);
        return wgt.intValue();
      }
    }
    return Integer.MAX_VALUE;
  }
}
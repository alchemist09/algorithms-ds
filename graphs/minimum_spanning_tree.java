import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;


class Graph2 {

  Map<Integer, HashMap<Integer, Integer>> edges;

  /**
   * Constructs an empty graph
   */
  public Graph2() {
    edges = new HashMap<Integer, HashMap<Integer, Integer>>();
  }

  /**
   * Constructs a graph with weighted edges
   * @param edgesMap - HashMap containing connected edge info 
   */
  public Graph2(Map<Integer, HashMap<Integer, Integer>> edgesMap) {
    edges = new HashMap<Integer, HashMap<Integer, Integer>>();
    this.loadGraph(edgesMap);
  }

  /**
   * Add a vertex
   */
  public void addVertex(int vertex) {
    if(!edges.containsKey(vertex)) {
      HashMap<Integer, Integer> adjacencyMap = new HashMap<Integer, Integer>();
      edges.put(vertex, adjacencyMap);
    }
  }

  /**
   * Add an edge between two vertices (u, v) in the graph
   * @param src - The source vertex
   * @param dest - The destination vertex
   * @param wgt - The weight associated with the edge
   */
  public void addEdge(int src, int dest, int wgt) {
    HashMap<Integer, Integer> adjacentVertices;
    if(!edges.containsKey(src)) {
      edges.put(src, new HashMap<Integer, Integer>(){{ put(dest, wgt); }});
    } else if(!edges.containsKey(dest)) {
      edges.put(dest, new HashMap<Integer, Integer>(){{ put(src, wgt); }});
    } else {
      adjacentVertices = edges.get(src);
      adjacentVertices.put(dest, wgt);
      adjacentVertices = edges.get(dest);
      adjacentVertices.put(src, wgt);
    }
  }

  /**
   * Determines whether an edge exists between two vertices
   */ 
  public boolean isEdge(int src, int dest) {
    if(!edges.containsKey(src)) {
      return false;
    }

    if(!edges.containsKey(dest)) {
      return false;
    }
    return true;
  }

  /**
   * Populate Graph with List of Edges
   */
  public void loadGraph(Map<Integer, HashMap<Integer, Integer>> edgesMap) {
    for(Map.Entry<Integer, HashMap<Integer, Integer>> entry : edgesMap.entrySet()) {
      Integer startVert = entry.getKey();
      System.out.println("Start Vert: " + startVert.intValue());
      HashMap<Integer, Integer> connectionsPair = entry.getValue();
      System.out.println("Connection Pair: " + connectionsPair);
      Iterator<Map.Entry<Integer, Integer>> iterMap = connectionsPair.entrySet().iterator();
      while(iterMap.hasNext()) {
        Map.Entry<Integer, Integer> pair = (Map.Entry<Integer,Integer>)iterMap.next();
        System.out.println("Pair: " + pair);
        this.addEdge(startVert, pair.getKey(), pair.getValue());
      }
      System.out.println("\r\n");
    }
  }
}
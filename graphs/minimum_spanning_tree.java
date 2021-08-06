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
}
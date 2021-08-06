import java.util.Map;
import java.util.HashMap;


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
}
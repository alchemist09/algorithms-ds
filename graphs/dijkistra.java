import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


class DiGraph2 {
  Map<Integer, ArrayList<HashMap<Integer, Integer>>> edges;

  /**
   * Construct an empty graph
   */
  public DiGraph2() {
    edges = new HashMap<Integer, ArrayList<HashMap<Integer, Integer>>>();
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
}
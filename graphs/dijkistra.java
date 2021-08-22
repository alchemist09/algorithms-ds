import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;


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

  /**
   * Checks whether graph is empty, i.e has no vertices
   */
  public boolean isEmpty() {
    return edges.size() == 0 ? true : false;
  }

  /**
   * Returns edges that a particular vertex is directed towards
   * @param int u - The vertex for which its edges are being retrieved
   */
  public List<HashMap<Integer, Integer>> getEdges(int u) {
    return edges.get(u);
  }
}

class MapComparator2 implements Comparator<Map<Integer, Integer>> {
  public int compare(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
    Set<Integer> map1_keys = map1.keySet();
    Set<Integer> map2_keys = map2.keySet();
    Integer[] dist1 = map1_keys.toArray(new Integer[map1_keys.size()]);
    Integer[] dist2 = map2_keys.toArray(new Integer[map2_keys.size()]);
    return dist1[0].intValue() - dist2[0].intValue();
  }
}

class Dijkistra {
  // Directed Graph on which to find the shortest path
  DiGraph2 Graph;

  public Dijkistra(DiGraph2 G) {
    Graph = G;
  }
}
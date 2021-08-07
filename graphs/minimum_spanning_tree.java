import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Comparator;


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
      HashMap<Integer, Integer> connectionsPair = entry.getValue();
      Iterator<Map.Entry<Integer, Integer>> iterMap = connectionsPair.entrySet().iterator();
      while(iterMap.hasNext()) {
        Map.Entry<Integer, Integer> pair = (Map.Entry<Integer,Integer>)iterMap.next();
        this.addEdge(startVert, pair.getKey(), pair.getValue());
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
   * Get the weight associated with a particular edge
   * @param src The source vertex
   * @param dest The destination vertex
   * @return the edge weight
   */
  public int getEdgeWeight(int src, int dest) {
    if(!this.isEdge(src, dest)) {
      return -1;
    }
    HashMap<Integer, Integer> endVertWgtPairs = edges.get(src);
    Iterator<Map.Entry<Integer, Integer>> iterMap = endVertWgtPairs.entrySet().iterator();
    while(iterMap.hasNext()) {
      Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>)iterMap.next();
      if(pair.getKey().intValue() == dest) {
        return pair.getValue().intValue();
      }
    }
    return -1;
  }
}




class MapComparator implements Comparator<Map<Integer, Integer>> {
  public int compare(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
    int map1_key = map1.entrySet().iterator().next().getKey().intValue();
    int map2_key = map2.entrySet().iterator().next().getKey().intValue();
    return map1_key - map2_key;
  }
}

class Edge implements Comparable<Edge> {
  public int compareTo(Edge other) {
    return 0;
  }
}
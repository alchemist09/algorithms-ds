import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;

class Main4 {
  public static void main(String[] args) {
    Map<Integer, ArrayList<HashMap<Integer, Integer>>> graphEdges =
    new HashMap<Integer, ArrayList<HashMap<Integer, Integer>>>();

    // Add vertices adjacent to vertex 1
    graphEdges.put(1, new ArrayList<HashMap<Integer, Integer>>(
      Arrays.asList(
        new HashMap<Integer, Integer>(){{ put(2, 2); }},
        new HashMap<Integer, Integer>(){{ put(3, 4); }}
      )
    ));

    // Add vertices adjacent to vertex 2
    graphEdges.put(2, new ArrayList<HashMap<Integer, Integer>>(
      Arrays.asList(
        new HashMap<Integer, Integer>(){{ put(4, 7); }},
        new HashMap<Integer, Integer>(){{ put(3, 1); }}
      )
    ));

    // Add vertices adjacent to vertex 3
    graphEdges.put(3, new ArrayList<HashMap<Integer, Integer>>(
      Arrays.asList(
        new HashMap<Integer, Integer>(){{ put(5, 3); }}
      )
    ));

    // Add vertices adjacent to vertex 4
    graphEdges.put(4, new ArrayList<HashMap<Integer, Integer>>(
      Arrays.asList(
        new HashMap<Integer, Integer>(){{ put(6, 1); }}
      )
    ));

    // Add vertices adjacent to vertex 5
    graphEdges.put(5, new ArrayList<HashMap<Integer, Integer>>(
      Arrays.asList(
        new HashMap<Integer, Integer>(){{ put(6, 5); }},
        new HashMap<Integer, Integer>(){{ put(4, 2); }}
      )
    ));

    graphEdges.put(6, new ArrayList<HashMap<Integer, Integer>>(
      Arrays.asList(
        new HashMap<Integer, Integer>(){{ put(Integer.MAX_VALUE, 0); }}
      )
    ));
  }
}


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

  // Map to keep track of the predecessor vertex while calculating shortest path
  Map<Integer, Integer> pred = new HashMap<Integer, Integer>();

  public Dijkistra(DiGraph2 G) {
    Graph = G;
  }

  /**
   * Calculates shortest path from source vertex to every other vertex on map
   * @param src
   * @return
   */
  public Map<Integer, Integer> ShortestPath(int src) {
    Set<Integer> vertices = Graph.edges.keySet();

    // Initialize a distance map D, that holds the best known distance of 
    // every vertex v in G from src
    Map<Integer, Integer> D = new HashMap<Integer, Integer>();
    for(Integer vertex : vertices) {
      D.put(vertex, Integer.MAX_VALUE);
    }

    // Set the weights of vertices connected to src as initial values in D
    List<HashMap<Integer, Integer>> adjToSrc = Graph.edges.get(src);
    for(Map<Integer, Integer> vertDistPair : adjToSrc) {
      Map.Entry<Integer, Integer> adjVertex = vertDistPair.entrySet().iterator().next();
      D.put(adjVertex.getKey(), adjVertex.getValue());
      // Specify the src vertex as the predecessor of the vertices it connects to
      pred.put(adjVertex.getKey(), src);
    }

    // Distance of src vertex from itself is zero
    D.put(src, 0);

    // Initialize a PriorityQueue that holds key-value pairs of (distance-vertex)
    // maps, where distance(key) is the distance of the value(vertex) from
    // the start vertex
    PriorityQueue<Map<Integer, Integer>> PQ = 
    new PriorityQueue<Map<Integer, Integer>>(new MapComparator2());

    // Add all vertices to priority queue
    for(Map.Entry<Integer, Integer> entry : D.entrySet()) {
      int vertex = entry.getKey();
      int dist = entry.getValue();
      if(vertex == src) {
        continue;
      }
      PQ.add(new HashMap<Integer, Integer>(1){{ put(dist, vertex); }});
    }

    Set<Integer> cloud = new HashSet<Integer>();
    cloud.add(src);

    while(!PQ.isEmpty()) {
      Map<Integer, Integer> distVertMap = PQ.remove();
      Map.Entry<Integer, Integer> mapEntry = distVertMap.entrySet().iterator().next();
      Integer u = mapEntry.getValue();
      cloud.add(u);
      
      // Get all vertices adjacent to u on its outging edges
      for(Map<Integer, Integer> vertObj : Graph.edges.get(u)) {
        Map.Entry<Integer, Integer> currEntry = vertObj.entrySet().iterator().next();
        Integer v = currEntry.getKey();
        if(!cloud.contains(v) && D.get(v) != null) {
          int wgt = Graph.getEdgeWeight(u.intValue(), v.intValue());
          if(D.get(u) + wgt < D.get(v)) {
            D.put(v, (D.get(u) + wgt));
            pred.put(v, u);
            // change key of vertex v in PQ to D[v]
            PQ.add(new HashMap<Integer, Integer>(){{ put(D.get(v), v); }});
          }
        }
      }
    }

    return D;
  }

  /**
   * Return the sequence of vertices followed to arrive at the shortest
   * path from specified start vertex to end vertex
   * @param src - The start vertex
   * @param dest - The end vertex
   * @return List - The shortest path
   */
  public List<Integer> GetShortestPath(int src, int dest) {
    List<Integer> path = new ArrayList<Integer>();
    path.add(dest);
    while(dest != src) {
      dest = pred.get(dest);
      path.add(0, dest);
    }
    return path;
  }
}
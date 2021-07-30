import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

class Main {
  public static void main(String[] args) {
    Map<Integer, ArrayList<HashMap<Integer, Integer>>> graphEdges = new HashMap<Integer, ArrayList<HashMap<Integer, Integer>>>();

    // Specifiy the connections amongst vertices and respective edge weights
    ArrayList<HashMap<Integer, Integer>> destVertices = new ArrayList<HashMap<Integer, Integer>>();
  }
}

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

class AllPairsShortestPath {
  DiGraph graph;
  int[][] dist;
  int[][] pred;
  int N;

  public AllPairsShortestPath(DiGraph dg) {
    graph = dg;
    N = graph.edges.keySet().size();
    dist = new int[N][N]; // distance between two nodes
    pred = new int[N][N]; // predecessor of a particular node

    // Prefill every cell of dist[] table
    for(int[] row : dist) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }

    // Prefill every cell of pred[] table
    for(int[] row : pred) {
      Arrays.fill(row, -1);
    }
  }

  /**
   * Calculate the Shortest Path Between Any Two Vertices
   * @return Returns matrix whose entries are weights of shortest path btwn any
             two vertices
   */
  public int[][] calculateAPSP() {
    // populate the dist and pred tables with values from graph
    for(int u=0; u < N; u++) {
      for(int v=0; v < N; v++) {
        if(u == v) {
          dist[u][v] = 0;
          // pred[u][v] = -1;
        } else if(graph.isEdge(u, v)) {
          dist[u][v] = graph.getEdgeWeight(u, v);
          pred[u][v] = u;
        } 
      }
    }

    // Calculate shortest path between any two vertices and populate dist table 
    // with the corresponding values
    for(int mid=0; mid < N; mid++) {
      for(int i=0; i < N; i++) {
        for(int j=0; j < N; j++) {
          if(dist[i][mid] != Integer.MAX_VALUE 
            && dist[mid][j] != Integer.MAX_VALUE
            && dist[i][mid] + dist[mid][j] < dist[i][j]) {
              pred[i][j] = pred[mid][j];
              dist[i][j] = dist[i][mid] + dist[mid][j];
            }
        }
      }
    }

    return dist;
  }

  /**
   * Construct the Shortest Path between two vertices
   * @param src The starting vertex
   * @param dest The end vertex
   * @return Returns List whose entries are the order of vertices to the SP
   */
  public List<Integer> printPath(int src, int dest) {
    List<Integer> path = new ArrayList<Integer>();
    path.add(dest);
    while(dest != src) {
      int predVertex = pred[src][dest];
      path.add(0, predVertex);
      dest = predVertex;
    }
    return path;
  }
}
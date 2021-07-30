import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    Graph g = new Graph();
    g.addVertex(2);
    g.addVertex(3);
    g.addVertex(4);
    g.addVertex(5);

    System.out.println(g.isEdge(2, 3));
    g.addEdge(2, 3);
    System.out.println(g.isEdge(2, 3));
    System.out.println(g.isEdge(3, 2));
    g.addEdge(2, 5);
    System.out.println(g.isEdge(2, 5));
    System.out.println(g.isEdge(2, 4));
    System.out.println(g.edges);

    // GRAPH TRAVERSAL 
    System.out.println("\r\nGRAPH TRAVERSAL");
    // Initialize the Adjacency List
    Map<Integer, ArrayList<Integer>> edgeList2 = new HashMap<Integer, ArrayList<Integer>>();
    edgeList2.put(1, new ArrayList<Integer>(Arrays.asList(2, 3, 5)));
    edgeList2.put(2, new ArrayList<Integer>(Arrays.asList(1, 4)));
    edgeList2.put(3, new ArrayList<Integer>(Arrays.asList(1)));
    edgeList2.put(4, new ArrayList<Integer>(Arrays.asList(2, 5)));
    edgeList2.put(5, new ArrayList<Integer>(Arrays.asList(1, 4)));

    Graph g3 = new Graph();
    g3.loadGraph(edgeList2);
    System.out.println(g3.edges);

    // Do A DFS
    DepthFirstTraversal dfs1 = new DepthFirstTraversal(g3, 3);
    // Print Predecessor 
    System.out.println("Predecessor:  " + dfs1.pred);
    System.out.println("Colors: " + dfs1.color);
    System.out.println("Edges for v1: " + g3.getEdges(1));

    // Print Solution
    System.out.println("\r\nSolution for DFS");
    System.out.println(dfs1.solution(2));
  }
}

class Graph {

  Map<Integer, ArrayList<Integer>> edges;

  /**
   * Constructs an empty graph
   */
  public Graph() {
    edges = new HashMap<Integer, ArrayList<Integer>>();
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

  /**
   * Do a DFS of the graph vertices
   * @param u vertex from where to begin DFS traversal
   */
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
    color.replace(u, Black);
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
class DiGraph {

  Map<Integer, ArrayList<HashMap<Integer, Integer>>> edges;

  /**
   * Constructs an empty graph
   */
  public DiGraph() {
    edges = new HashMap<Integer, ArrayList<HashMap<Integer, Integer>>>();
  }
}
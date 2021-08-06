import java.util.Map;
import java.util.HashMap;


class Graph2 {

  Map<Integer, HashMap<Integer, Integer>> edges;

  public Graph2() {
    edges = new HashMap<Integer, HashMap<Integer, Integer>>();
  }

  public Graph2(Map<Integer, HashMap<Integer, Integer>> edgesMap) {
    edges = new HashMap<Integer, HashMap<Integer, Integer>>();
    this.loadGraph(edgesMap);
  }
}
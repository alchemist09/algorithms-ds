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
}
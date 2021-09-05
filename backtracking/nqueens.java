import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class NQueens {
  int count;
  int num_queens;
  Integer[] nTuple;
  List<Integer[]> solutions;

  public NQueens(int no_of_queens) {
    count = 0;
    num_queens = no_of_queens;
    nTuple = new Integer[no_of_queens];
    Arrays.fill(nTuple, new Integer(-1));
    solutions = new ArrayList<Integer[]>();
    System.out.println("Constructor nTuple: " + Arrays.asList(nTuple) + "\r\n");
  }
}
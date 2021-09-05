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

  /**
   * Determine whether queen can be plcaed on a particular cell
   * @param k - number of rows on chess board
   * @param thisCol - the current column under consideration
   * @return True/False whether queen can be placed on a certain cell
   */
  public boolean canPlaceQueen(int k, int thisCol) {
    // System.out.println("nTuple: " + Arrays.asList(nTuple));
    for(int currRow=0; currRow < k; currRow++) {
      // System.out.println("nTuple[currRow]: " + nTuple[currRow]);
      // System.out.println("nTuple[currRow].intValue(): " + nTuple[currRow].intValue());
      // System.out.println("\r\n");
      if(nTuple[currRow].intValue() == thisCol ||
         (Math.abs(nTuple[currRow].intValue() - thisCol) == Math.abs(k-currRow))) {
          return false;
        }
    }
    return true;
  }
}
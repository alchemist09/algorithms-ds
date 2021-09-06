import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class NQueens {
  int count;
  int num_queens;
  Integer[] nTuple;
  List<Integer[]> solutions;

  /**
   * Coonstructor
   * @param no_of_queens - number of queens to be placed on chess board without attacking each other
   */
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
    for(int currRow=0; currRow < k; currRow++) {
      if(nTuple[currRow].intValue() == thisCol ||
         (Math.abs(nTuple[currRow].intValue() - thisCol) == Math.abs(k-currRow))) {
          return false;
        }
    }
    return true;
  }

  /**
   * Tries to place all queens on chess board
   * @param row - row on chessboard
   * @return true if it succeeded placing all queens on chess board, false otherwise
   */
  public boolean arrangeQueens(int row) {
    if(row == num_queens) {
      return true;
    }

    for(int col=0; col < num_queens; col++) {
      if(this.canPlaceQueen(row, col)) {
        nTuple[row] = col;
        if(row == (num_queens -1)) {
          Integer[] result = nTuple.clone();
          solutions.add(result);
          count++;
          return true;
        }
        arrangeQueens(row + 1); 
      }
    }
    return false;
  }

  /**
   * Prints the different formations that were possible to place the queens
   */
  public void printAllSolutions() {
    System.out.println("Solution Size: " + solutions.size());
    for(Integer[] soln : solutions) {
      System.out.println(Arrays.asList(soln));
    }
  }

  /**
   * Prints the number of different ways it possible to place N-queens on board
   */
  public void numSolutions() {
    System.out.println("No. of solutions: " + count);
  }
}
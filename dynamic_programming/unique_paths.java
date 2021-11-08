/**
 Given a 2-D array, find the total number of paths possible from top-left cell
 to bottom-right cell if we are allowed to move only rightward and downward.

 cell(m, n) can be reached from only two possible directions
  1. Top, i.e the cell above it (m-1, n)
  2. Left, i.e the cell on its left (m, n-1)

 Suppose there are P1 ways of reaching cell(m-1, n), and P2 ways of reaching 
 cell(m, n-1), then we can reach cell(m, n) in P1 + P2 unique ways, via
 cell(m-1, n) and cell(m, n-1). This defines our recursion.

 The terminating condition is when we hit the topmost row or leftmost column.
 There's only one way to reach any cell in top row,(going rightward from (0,0)).
 Similarly, there's only one way to reach any cell in leftmost column 
 (going downwards from (0, 0)). The number of ways to reach zero is 0, since we
 are already there.
*/

package dynamic_programming;

import java.util.Arrays;

class UniquePaths {
  public static void main(String[] args) {
    System.out.println("Brute Force======================");
    System.out.println(uniquePathsBF(2, 5));
    System.out.println(uniquePathsBF(7, 3));

    System.out.println("\r\nMemoization======================");
    System.out.println(uniquePathsBF(2, 5));
    System.out.println(uniquePathsBF(7, 3));

    System.out.println("\r\nDynamic Programming======================");
    System.out.println(uniquePathsBF(2, 5));
    System.out.println(uniquePathsBF(7, 3));
  }

  /**
   * Brute force approach of calculating the number of unique paths from top-left to bottom-right
   * @param m Grid dimension along the Y-axis
   * @param n Grid dimension along the X-axis
   * @return The total number of unique ways to traverse the grid
   */
  public static int uniquePathsBF(int m, int n) {
    if(m == 0 && n == 0) { return 0; } // cell (0, 0)
    if(m == 0 || n == 0) { return 1; } // top-most column or left-most row
    return uniquePathsBF(m-1, n) + uniquePathsBF(m, n-1);
  }

  /**
   * Top-Down memoization approach of calculating the number of unique paths from top-left to bottom-right
   * @param m Grid dimension along the Y-axis
   * @param n Grid dimension along the X-axis
   * @return The total number of unique ways to traverse the grid
   */
  public static int uniquePathsMemo(int m, int n) {
    if(m == 1 || n == 1) {
        return 1;
    }
    
    int[][] T = new int[m][n];
    
    for(int i=0; i < m; i++) {
        Arrays.fill(T[i], -1);
    }
    
    return uniquePathsUtil(T, m, n);
  }

  /**
   * Recursive utility function to help with the calculation of unique paths in grid
   * @param cache Memoization table to store pre-computed values
   * @param m Grid dimension along the Y-axis
   * @param n Grid dimension along the X-axis
   * @return The total number of unique ways to traverse the grid
   */
  private static int uniquePathsUtil(int[][] cache, int m, int n) {
    if(m == 1 || n == 1) {
        return 1;
    }
    
    if(cache[m-1][n-1] != -1) {
        return cache[m-1][n-1];
    }
    
    cache[m-1][n-1] = uniquePathsUtil(cache, m-1, n) + uniquePathsUtil(cache, m, n-1);
    return cache[m-1][n-1];
  }

  /**
   * Bottom-up Dynamic Programming approach of calculating the number of unique paths from top-left to bottom-right
   * @param m Grid dimension along the Y-axis
   * @param n Grid dimension along the X-axis
   * @return The total number of unique ways to traverse the grid
   */
  public static int uniquePathsDP(int m, int n) {
    int[][] table = new int[m][n];

    // cell 90, 0)
    table[0][0] = 0;
    
    // fill top row
    for(int i=1; i <= n; i++) {
      table[0][i] = 1;
    }

    // fill leftmost column
    for(int j=1; j <= m; j++) {
      table[j][0] = 1;
    }

    // fill rest of the table
    for(int i=1; i <= m; i++) {
      for(int j=1; j <= n; j++) {
        table[i][j] = table[i-1][j] + table[i][j-1];
      }
    }

    return table[m][n];
  }
}
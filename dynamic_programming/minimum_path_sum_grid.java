/**
 * Given a two-dimensional square matrix cost[][] of order M*N where cost[i][j] 
 * represent the cost of passing through cell(i,j). Total cost to reach a particular
 * cell is the sum of costs of all cells in that path (including the starting and
 * final cell). We can only move either downward or rightward. i.e, if we are at
 * cell(i, j), the we can either go to (i+1, j) or (i, j+1)
 * 
 * Write a function that return the minimum cost of moving from the top-left cell
 * to the bottom-right cell of the matrix. The first matrix below shows the cost
 * matrix while the second one shows the minimum cost path in that matrix.
 */

package dynamic_programming;

import java.util.Arrays;

class MinimumPathSumGrid {
  public static void main(String[] args) {
    int[][] costMatrix = {
      {1, 3, 5, 8},
      {4, 2, 1, 7},
      {4, 3, 2, 3}
    };

    int[][] grid = {
      {1, 3, 1},
      {1, 5, 1},
      {4, 2, 1}
    };

    System.out.println("Brute Force");
		System.out.println("[costMatrix] - Minimum cost path to cell (2, 3): " + minimumCostPath(costMatrix, 2, 3));
		System.out.println("[grid] - Minimum cost path to cell (2, 2): " + minimumCostPath(grid, 2, 2));

    System.out.println("\r\nMemoization");
		int cm_len = costMatrix.length;
		int cm_wid = costMatrix[0].length;
		int[][] memo1 = new int[cm_len][cm_wid];
		for(int i=0; i < cm_len; i++) {
		    Arrays.fill(memo1[i], Integer.MIN_VALUE);
		}
		
		int gridL = grid.length;
		int gridW= grid[0].length;
		int[][] memo2 = new int[gridL][gridW];
		for(int i=0; i < gridL; i++) {
		    Arrays.fill(memo2[i], Integer.MIN_VALUE);
		}
		
		System.out.println("[costMatrix] - Minimum cost path to cell (2, 3): " + minimumCostPathMemo(costMatrix, memo1, 2, 3));
		System.out.println("[grid] - Minimum cost path to cell (2, 2): " + minimumCostPathMemo(grid, memo2, 2, 2));

    System.out.println("\r\nDynamic Programming");
		System.out.println("[costMatrix] - Minimum cost path to cell (2, 3): " + minimumCostPathDP(costMatrix, 2, 3));
		System.out.println("[grid] - Minimum cost path to cell (2, 2): " + minimumCostPathDP(grid, 2, 2));
  }

  public static int getMin(int x, int y) {
    return x < y ? x : y;
  }

  /**
   * Brute Force Approach of Calculating the minimum cost of reaching specified cell in grid
   * @param cost Grid outlining the cost of moving through cells
   * @param m Target cell along Y-axis
   * @param n Target cell along X-axis
   * @return minimum cost incurred to reach cell mn
   */
  public static int minimumCostPath(int[][] cost, int m, int n) {
    // cell at top left
    if(m==0 && n==0) {
        return cost[0][0];
    }
    
    // top most row - we can only approach cell from left
    if(m==0) {
        return minimumCostPath(cost, m, n-1) + cost[0][n];
    }
    
    // left most column - we can only approach cell from top
    if(n==0) {
        return minimumCostPath(cost, m-1, n) + cost[m][0];
    }
    
    int x = minimumCostPath(cost, m-1, n);
    int y = minimumCostPath(cost, m, n-1);
    
    return getMin(x, y) + cost[m][n];
  }

  /**
   * Use Top-Down Memoization approach to calculate the minimum cost path sum in grid
   * @param cost Grid representing the cost incurred by passing through specific cells
   * @param cache Stores pre-computed problems 
   * @param m Cell value on Y-axis
   * @param n Cell value on X-axis
   * @return The minimmum cost one incurs to reach cell mn
   */
  public static int minimumCostPathMemo(int[][] cost, int[][]cache, int m, int n) {
	    
    // if value already exists in cache, return value 
    if(cache[m][n] != Integer.MIN_VALUE) {
        return cache[m][n];
    }
    
    // top left cell 
    if(m == 0 && n == 0) {
        return cost[0][0];
    }
    
    // top most row - we can only approach cell from left 
    if(m == 0) {
       return minimumCostPathMemo(cost, cache, m, n-1) + cost[0][n];
    }
    
    // left most column - we can only approach cell from top 
    if(n == 0) {
        return minimumCostPathMemo(cost, cache, m-1, n) + cost[m][0];
    }
    
    int x = minimumCostPathMemo(cost, cache, m-1, n);
    int y = minimumCostPathMemo(cost, cache, m, n-1);
    
    cache[m][n] = getMin(x, y) + cost[m][n];
    return cache[m][n];
  }

  /**
   * Use Bottom-Up Dynamic Programming Approach to calculate the minimum cost path through grid
   * @param cost Cost matrix
   * @param m Target cell along Y-axis
   * @param n Target cell along X-axis
   * @return Minimum cost to reach cell mn in grid
   */
  public static int minimumCostPathDP(int[][] cost, int m, int n) {
    int[][] dp = new int[m+1][n+1];
    
    // top left
    dp[0][0] = cost[0][0];
    
    // top most row 
    for(int j=1; j <= n; j++) {
        dp[0][j] = dp[0][j-1] + cost[0][j];
    }
    
    // left most column 
    for(int i=1; i <= m; i++) {
        dp[i][0] = dp[i-1][0] + cost[i][0];
    }
    
    // fill the rest of the cells 
    for(int i=1; i <= m; i++) {
        for(int j=1; j <= n; j++) {
            dp[i][j] = getMin(dp[i-1][j], dp[i][j-1]) + cost[i][j];
        }
    }
    
    return dp[m][n];
  }
}
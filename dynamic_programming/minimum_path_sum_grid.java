package dynamic_programming;

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
  }

  public static int getMin(int x, int y) {
    return x < y ? x : y;
  }

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
}
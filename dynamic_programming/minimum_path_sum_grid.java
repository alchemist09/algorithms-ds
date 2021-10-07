package dynamic_programming;

class MinimumPathSumGrid {
  public static void main(String[] args) {
    
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
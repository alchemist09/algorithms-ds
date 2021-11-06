package dynamic_programming;

import java.util.Arrays;

class UniquePaths {
  public static void main(String[] args) {
    System.out.println(uniquePathsBF(2, 5));
    System.out.println(uniquePathsBF(7, 3));
  }

  public static int uniquePathsBF(int m, int n) {
    if(m == 0 && n == 0) { return 0; } // cell (0, 0)
    if(m == 0 || n == 0) { return 1; } // top-most column or left-most row
    return uniquePathsBF(m-1, n) + uniquePathsBF(m, n-1);
  }

  public static int uniquePathsMemo(int m, int n) {
    if(m == 1 || n == 1) {
        return 1;
    }
    
    int[][] T = new int[m][n];
    
    for(int i=0; i < m; i++) {
        Arrays.fill(T[i], -1);
    }
    
    System.out.println(Arrays.deepToString(T));
    
    return uniquePathsUtil(T, m, n);
  }

  private static int uniquePathsUtil(int[][] cache, int m, int n) {
    if(m == 1 || n == 1) {
        return 1;
    }
    
    if(cache[m-1][n-1] != -1) {
        return cache[m-1][n-1];
    }
    
    cache[m-1][n-1] = uniquePathsUtil(cache, m-1, n) + uniquePathsUtil(cache, m, n-1);
    System.out.println(Arrays.deepToString(cache));
    return cache[m-1][n-1];
  }
}
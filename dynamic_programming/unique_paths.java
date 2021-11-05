package dynamic_programming;

class UniquePaths {
  public static void main(String[] args) {
    
  }

  public static int uniquePathsBF(int m, int n) {
    if(m == 0 && n == 0) { return 0; } // cell (0, 0)
    if(m == 0 || n == 0) { return 1; } // top-most column or left-most row
    return uniquePathsBF(m-1, n) + uniquePathsBF(m, n-1);
  }
}
package dynamic_programming;

class LongestCommonSubstring {
  public static void main(String[] args) {
    
  }

  public static int lcs(String a, String b) {
    if(a == null || b == null) {
        return 0;
    }
    
    int m = a.length();
    int n = b.length();
    
    if(m == 0 || n == 0) {
        return 0;
    }
    
    int max = 0;
    int[][] dp = new int[m][n];
    
    // fill rest of table
    for(int i=0; i < m; i++) {
        for(int j=0; j < n; j++) {
            if(a.charAt(i) == b.charAt(j)) {
                if(i==0 || j==0) {
                    dp[i][j] = 1;
                } else {
                   dp[i][j] = dp[i-1][j-1] + 1;  
                }
                
                if(dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
    }
    return max;
}
}
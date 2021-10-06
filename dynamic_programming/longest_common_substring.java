package dynamic_programming;

class LongestCommonSubstring {
  public static void main(String[] args) {
    String str1 = "abc";
		String str2 = "a";

    System.out.println(lcs(str1, str2));
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

  public static String lcs2(String a, String b) {
    if(a == null || b == null) {
        return "";
    }
    
    int m = a.length();
    int n = b.length();
    
    if(m == 0 || n == 0) {
        return "";
    }
    
    String res = "";
    int maxLen = 0;
    int[][] dp = new int[m][n];
    
    for(int i=0; i < m; i++) {
        for(int j=0; j < n; j++) {
            if(a.charAt(i) == b.charAt(j)) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                
                if(dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    res = a.substring(i-maxLen + 1, i+1);
                }
            }
        }
    }
    return res;
  }
}
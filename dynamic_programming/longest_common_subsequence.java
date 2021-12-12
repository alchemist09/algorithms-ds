package dynamic_programming;

import java.util.Arrays;

class LongestCommonSubsequence {
  public static void main(String[] args) {
    String s1 = "abcdef";
		String s2 = "apqbrf";

    System.out.println("LCS btwn 'abcdef' and 'apqbrf': " + lcsRec(s1, s2));
  }

  public static int lcsRec(String m, String n) {
    // if length of either input string is zero, there's no common subsequence
    if(m.length() == 0 || n.length() == 0) {
      return 0;
    }

    int m_len = m.length();
    int n_len = n.length();
  
    // case where last character of either substring is same
    if(m.charAt(m_len-1) == n.charAt(n_len-1)) {
      return 1 + lcsRec(m.substring(0, m_len-1), n.substring(0, n_len-1));
    } else {
      // if ending characters are not same, return maximum of two recursive calls
      return Math.max(lcsRec(m.substring(0, m_len-1), n), lcsRec(m, n.substring(0, n_len-1)));
    }
  }

  public static int lcsMemo(String text1, String text2) {
    if((text1 == null || text1.length() == 0) || (text2 == null || text2.length() == 0)) {
        return 0;
    }
    
    int m = text1.length();
    int n = text2.length();
    
    int[][] T = new int[m][n];
    for(int i=0; i < T.length; i++) {
        Arrays.fill(T[i], -1);
    }
    
    int result = lcsUtil(T, text1, text2);
    // System.out.println(Arrays.deepToString(T));
    return result;
  }
}
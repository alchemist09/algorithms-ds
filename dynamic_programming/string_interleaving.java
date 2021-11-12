/**
 String C is said to be an intervleaving of string A and B if it contains all
 the characters from A and B, and the relative order of characters of both of 
 the strings is preserved in C. For example:
      A = xyz
      B = abcd
      C = xabyczd (intervleaving of A and B)

      A = bcc
      B = bbca
      C = bbcbcac (intervleaving of A and B)
*/

package dynamic_programming;

class StringInterleave {
  public static void main(String[] args) {
    String str1 = "aabcc";
		String str2 = "dbbca";
		String str3 = "aadbbcbcac";

    String str4 = "abc";
		String str5 = "def";
		String str6 = "dcabef";
    
    System.out.println("BRUTE FORCE=======================");
    System.out.println(isInterleaveBF(str1, str2, str3));
    System.out.println(isInterleaveBF(str4, str5, str6));

    System.out.println("\r\nDYNAMIC PROGRAMMING=======================");
    System.out.println(isInterleaveBF(str1, str2, str3));
    System.out.println(isInterleaveBF(str4, str5, str6));
  }

  /**
   * Determine if a string is an interleaving of two strings using brute force approach
   * @param s1 The first string 
   * @param s2 The second string
   * @param s3 The interleaved string
   * @return Returns whether s3 is an interleaving of s1 and s2
   */
  public static boolean isInterleaveBF(String s1, String s2, String s3) {
    // if all three strings are empty return true
    if(s1.length() == 0 && s2.length() == 0 && s3.length() == 0) {
        return true;
    }
    
    // if s3 is empty, s1 or s2 or both are not empty
    if(s3.length() == 0) {
        return false;
    }
    
    // if both s1 and s2 are empty but s3 is not
    if(s1.length() == 0 && s2.length() == 0) {
        return false;
    }
    
    boolean first, second, third;
    first = second = third = false;
    
    if((s1.length() != 0 && s1.charAt(0) == s3.charAt(0)) && (s2.length() != 0 && s2.charAt(0) == s3.charAt(0))) {
        third = isInterleaveBF(s1.substring(1), s2, s3.substring(1)) || isInterleaveBF(s1, s2.substring(1), s3.substring(1));
    }
        
    if(s1.length() != 0 && (s1.charAt(0) == s3.charAt(0))) {
        first = isInterleaveBF(s1.substring(1), s2, s3.substring(1));
    }
    
    if(s2.length() != 0 && (s2.charAt(0) == s3.charAt(0))) {
        second = isInterleaveBF(s1, s2.substring(1), s3.substring(1));
    }
    
    return (first || second || third);
  }

  public static boolean isInterleaveDP(String A, String B, String C) {
    if(C.length() != (A.length() + B.length())) {
      return false;
    }

    boolean[][] T = new boolean[A.length()+1][B.length()+1];
    T[0][0] = true;

    // first row, case where A is empty
    for(int j=1; j <= B.length(); j++) {
      if(B.charAt(j-1) != C.charAt(j-1)) {
        T[0][j] = false;
      } else {
        T[0][j] = T[0][j-1];
      }
    }

    // first column, case where B is empty
    for(int i=1; i <= A.length(); i++) {
      if(A.charAt(i-1) != C.charAt(i-1)) {
        T[i][0] = false;
      } else {
        T[i][0] = T[i-1][0];
      }
    }

    for(int i=1; i < A.length(); i++) {
      for(int j=1; j < B.length(); j++) {
        int k = i+j;
        if(A.charAt(i-1) == C.charAt(k-1) && B.charAt(j-1) == C.charAt(k-1)) {
          T[i][j] = T[i-1][j] || T[i][j-1];
        } else if(A.charAt(i-1) == C.charAt(k-1)) {
          T[i][j] = T[i-1][j];
        } else if(B.charAt(j-1) == C.charAt(k-1)) {
          T[i][j] = T[i][j-1];
        } else {
          T[i][j] = false;
        }
      }
    }

    return T[A.length()][B.length()];
  }
}
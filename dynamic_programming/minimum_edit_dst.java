/**
 The words COMPUTER and COMMUTER are very similar, and an update of just one 
 letter P -> M will change the first word into the second word. Similarly, 
 the word SPORT can be changed into SORT by deleting one character, P, or
 equivalently SORT can be changed into SPORT by inserting one character P.

 Edit distance between two strings is defined as the minimum number of character
 operations (update, insert, delete) required to convert one string into another.

 Given two strings, str1 and str2, and the following three operations that can
 be performed on str1,
         1. insert
         2. replace(updarte)
         3. delete

 Find the minimum number of operations required to convert str1 into str2. For 
 example, if the input string are CAT and CAR, then the edit distance is 1.

       C A T
           | replace
           v
       C A R

 Similarly, if the two input strings are SUNDAY and SATURDAY, then the edit distance
 is 3.
      
       S                     U   N D A Y
            |     |              |
            v     v              v
       S    A     T          U   R D A Y
         insert insert        replace
            

*/

package dynamic_programming;

import java.util.Arrays;

class MinimumEditDistance {
  public static void main(String[] args) {
    String str1 = "CAT";
		String str2 = "CARE";
		System.out.println(minEditDistance(str1, str2));

    String str3 = "SUNDAY";
    String str4 = "SATURDAY";
    System.out.println(minEditDistanceDP(str3, str4));
  }

  /**
   * Get the minimum number of deletions, insertions and updations needed
   * to convert one string into another using brute force approach
   * @param str1 The First string
   * @param str2 The Second string 
   * @return minumum no. of edits required to convert str1 into str2
   */
  public static int minEditDistance(String str1, String str2) {
    // if str1 is empty or null, we need to insert characters of str2
    if(str1 == null || str1.length() == 0) {
      return str2.length();
    }

    // if str2 is empty or null, we need to delete all characters of str1
    if(str2 == null || str2.length() == 0) {
      return str1.length();
    }

    // if first characters of both strings are the same, then ignore it
    // and find the edit distance between the remaining characters
    if(str1.charAt(0) == str2.charAt(0)) {
      return minEditDistance(str1.substring(1), str2.substring(1));
    }

    // find the edit distance of all three operations
    int d, u, i = 0;

    d = minEditDistance(str1.substring(1), str2);
    u = minEditDistance(str1.substring(1), str2.substring(1));
    i = minEditDistance(str1, str2.substring(1));

    // return minimum of the three values + 1
    return getMin(d, u, i) + 1;
  }

  /**
   * Gets the minimum of three integers
   * @param x first integer
   * @param y second integer
   * @param z third integer
   * @return minimum value amongst three integers
   */
  public static int getMin(int x, int y, int z) {
    return (x < y) ? ((x < z) ? x : z) : ((y < z) ? y : z );
  }

  /**
   * Calculate the minimum number  of edits need to change word1 into word2 
   * using bottom-up dynamic programming approach
   * @param word1 the first word
   * @param word2 the second word
   * @return minimum no. of edits needed to convert one word into another
   */
  public static int minEditDistanceDP(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();
    
    int[][] T = new int[m+1][n+1];
    
    for(int i=0; i < T.length; i++) {
        Arrays.fill(T[i], -1);
    }
    
    System.out.println(Arrays.deepToString(T));
    
    // fill left most column where word2 is empty
    for(int i=0; i <= m; i++) {
        T[i][0] = i;
    }
    
    // fill the top most row where word1 is empty
    for(int j=0; j <= n; j++) {
        T[0][j] = j;
    }
    
    // fill rest of the cache table
    for(int i=1; i <= m; i++) {
      for(int j=1; j <= n; j++) {
        if(word1.charAt(i-1) == word2.charAt(j-1)) {
            T[i][j] = T[i-1][j-1];
        } else {
            int tempMin = getMin2(T[i-1][j], T[i][j-1]);
            T[i][j] = getMin2(tempMin, T[i-1][j-1]) + 1;
        }
      }
    }
    
    System.out.println(Arrays.deepToString(T));
    return T[m][n];
  }

  /**
   * Get the minimum between two integer values
   * @param a integer value
   * @param b integer value
   * @return the minmum of 2 integer values
   */
  public static int getMin2(int a, int b) {
    return (a < b) ? a : b;
  }
}
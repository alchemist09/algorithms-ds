package dynamic_programming;

import java.util.Arrays;

class LongestPalindromicSumSubstring {
  public static void main(String[] args) {
    String str1 = "13267224";
		System.out.println("longest palindromic sum substring of 13267224: " + longestPalindromicSumSubstring(str1));
  }

  public static int longestPalindromicSumSubstring(String str) {
    int N = str.length();
    int[] sum = new int[N+1];
    
    System.out.println(Arrays.toString(sum));
    
    for(int i=1; i <= N; i++) {
      sum[i] = sum[i-1] + (str.charAt(i-1) - '0');
    }
    
    System.out.println(Arrays.toString(sum));
    
    int maxLen = 0;
    
    for(int j=0; j < str.length()-1; j++) {
      for(int k=j+1; k < str.length(); k+=2) {
        // get length of substring 
        int substr = (k-j) + 1;
            
        System.out.println("Substring: " + str.substring(j, k+1));
            
        // get substring midpoint 
        int mid = j + (substr/2);
        System.out.println("mid: " + mid);
            
        if((sum[mid] - sum[j] == sum[k+1] - sum[mid]) && substr > maxLen) {
          maxLen = substr;
        }
      }
    }
    
    return maxLen;
  }
}
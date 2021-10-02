package dynamic_programming;


class LongestPalindromicSumSubstring {
  public static void main(String[] args) {
    String str1 = "13267224";
		System.out.println("longest palindromic sum substring of 13267224: " + longestPalindromicSumSubstring(str1));
  }

  public static int longestPalindromicSumSubstring(String str) {
    int N = str.length();
    int[] sum = new int[N+1];
    
    for(int i=1; i <= N; i++) {
      sum[i] = sum[i-1] + (str.charAt(i-1) - '0');
    }
    
    int maxLen = 0;
    
    for(int j=0; j < str.length()-1; j++) {
      for(int k=j+1; k < str.length(); k+=2) {
        // get length of substring 
        int substr = (k-j) + 1;
            
        // get substring midpoint 
        int mid = j + (substr/2);
            
        if((sum[mid] - sum[j] == sum[k+1] - sum[mid]) && substr > maxLen) {
          maxLen = substr;
        }
      }
    }
    
    return maxLen;
  }

  public static int longestPalindromicSumSubstring2(String s) {
		if(s.length() == 0) { return 0; }
		if(s.length() == 1) { return 1; }
		int N = s.length();
		int maxLen = 0;
		
		for(int i=0; i < N; i++) {
			for(int j=i+1; j < N; j+=2) {
				int substring_length = (j-i) + 1;
				
				// if maxLen greater than length of current substring, do nothing
				if(maxLen > substring_length) {
					continue;
				}
				
				int half_len = (int)Math.floor(substring_length/2);
				int lSum=0;
				int rSum=0;
				
				// loop through either half of the substring computing sum of lSum and rSum
				for(int k=0; k < substring_length/2; k++) {
					lSum += Integer.parseInt(String.valueOf(s.charAt(i+k)));
					rSum += Integer.parseInt(String.valueOf(s.charAt(i+k+half_len)));
				}
				
				if(lSum == rSum) {
					maxLen = substring_length;
				}
			}
		}
		return maxLen;
	}
}
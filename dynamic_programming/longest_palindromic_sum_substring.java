package dynamic_programming;


class LongestPalindromicSumSubstring {
  public static void main(String[] args) {
    String str1 = "13267224";
		System.out.println("longest palindromic sum substring of 13267224: " + longestPalindromicSumSubstring(str1));

    String str2 = "146281";
		String str3 = "546374";
		
		System.out.println();
		System.out.println("longest palindromic sum substring of 146281: " + longestPalindromicSumSubstring2(str2));
		System.out.println("longest palindromic sum substring of 546374: " + longestPalindromicSumSubstring2(str3));

    System.out.println("\r\nDynamic Programming");
		System.out.println("DP - longest palindromic sum substring of 146281: " + longestPalindromicSumSubstringDP(str2));
		System.out.println("DP longest palindromic sum substring of 546374: " + longestPalindromicSumSubstringDP(str3));
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

  public static int longestPalindromicSumSubstringDP(String str) {
		int N = str.length();
		int maxLen = 0;
		int[][] sumTable = new int[N][N];
		
		// fill diagonals where i == j, single length substring 
		for(int i=0; i < N; i++) {
			sumTable[i][i] = (str.charAt(i) - '0');
		}
		
		for(int len=2; len <= N; len++) {
			for(int i=0; i < N - len + 1; i++) {
				int j = i + len - 1;
				int mid = len / 2;
				
				// sumTable[i][j] equals sum of substring from [i-mid] and from [mid-j]
				sumTable[i][j] = sumTable[i][j-mid] + sumTable[j-mid+1][j];
				
				// if substring is even length, check if either half is equal and whether
				// current length substring exceeds len 
				if(N % 2 == 0 && sumTable[i][j-mid] == sumTable[j-mid+1][j] && len > maxLen) {
					maxLen = len;
				}
			}
		}
		
		return maxLen;
	}

  public static int expand(String str, int low, int high, int max) {
		// variables to keep track of left sum and right sum of substring 
		int leftSum = 0;
		int rightSum = 0;
		
		while(low >= 0 && high < str.length()) {
			// expand in both directions
			leftSum  += (str.charAt(low) - '0');
			rightSum += (str.charAt(high) - '0');
			
			// check if leftSum and rightSum are equal
			if(leftSum == rightSum && (high - low) + 1 > max) {
				max = (high - low) + 1;
			}
			
			// expand in both directions
			low--;
			high++;
		}
		
		return max;
	}

  public static int longestPalindromicSumSubstringAlt(String str) {
		int maxLen = 0;
		
		// for every adjacent pair of characters as midpoints and expand in both directions
		for(int low=0; low < str.length() - 1; low++) {
			maxLen = expand(str, low, low + 1, maxLen);
		}
		
		return maxLen;
	}
}
package dynamic_programming;

class RodCutting {
  public static void main(String[] args) {
    int[] price = {0, 1, 5, 8, 9, 10, 17, 17, 20};
		int rod_len1 = 8;
		
		int[] price2 = {0, 3, 5, 8, 9, 10, 17, 17, 20};
    int rod_len2 = 8;

    System.out.println("Brute Force");
		System.out.println(cutRodBF(price, rod_len1));
		System.out.println(cutRodBF(price2, rod_len2));
		System.out.println("\r\n");

    System.out.println("Memoization");
		System.out.println(cutRodMemo(price, rod_len1));
		System.out.println(cutRodMemo(price2, rod_len2));
		System.out.println("\r\n");

    System.out.println("Dynamic Programming");
		System.out.println(cutRodDP(price, rod_len1));
		System.out.println(cutRodDP(price2, rod_len2));
		System.out.println("\r\n");
  }

  /**
   * Brute Force approach of solving the rod cutting problem
   * @param prices The price of the rods of different sizes
   * @param size The size of the rod to be cut up 
   * @return The maximum profit that can be derived by cutting up the rod into various sizes
   */
  public static int cutRodBF(int[] prices, int size) {
    if(prices == null || prices.length == 0 || size <= 0) {
        return 0;
    }
    
    int maxValue = Integer.MIN_VALUE;
    for(int rodSize = 1; rodSize <= size; rodSize++) {
        maxValue = Math.max(maxValue, prices[rodSize] + cutRodBF(prices, size - rodSize));
    }
    
    return maxValue;
  }

  /**
   * Top-Down memoization approach for solving the rod-cutting problem
   * @param prices The prices of rods of various sizes
   * @param size The size of the rod being cut up
   * @return The maximum profit derived that can be derived by cutting the rod into various sizes
   */
  public static int cutRodMemo(int[] prices, int size) {
    if(prices == null || prices.length == 0 || size <= 0) {
        return 0;
    }
    
    int[] memoTable = new int[size + 1];
    
    return cutRodUtil(prices, size, memoTable);
  }

  /**
   * A utiltiy function that handles the actual recursion with caching for the memoization method
   * @param prices The prices of rods of different sizes
   * @param size The size of the rod
   * @param cache Cache to store results of previously computed values
   * @return The maximum profit that can be derived from cutting the rod into various sizes
   */
  private static int cutRodUtil(int[] prices, int size, int[] cache) {
    if(cache[size] > 0) {
        return cache[size];
    }
    
    for(int rodSize=1; rodSize <= size; rodSize++) {
        cache[size] = Math.max(cache[size], prices[rodSize] + cutRodUtil(prices, size - rodSize, cache));
    }
    
    return cache[size];
  }

  /**
   * Bottom-Up Dynamic Programming approach for solving the rod cutting problem
   * @param prices Prices of rods of different lengths
   * @param size The size of the rod to be cut up
   * @return The maximum profit that can be derived from cutting the rod into various sizes
   */
  public static int cutRodDP(int[] prices, int size) {
    if(prices == null || prices.length == 0 || size == 0) {
        return 0;
    }
    
    int[] maxValue = new int[size + 1];
    
    // maxValue[i] stores the maximum value from cutting a rod of size i
    for(int i=1; i <= size; i++) {
        for(int j=1; j <= i; j++) {
            maxValue[i] = Math.max(maxValue[i], prices[j] + maxValue[i-j]);
        }
    }
    
    return maxValue[size];
  }

  /**
   * Bottom-up Dynamic Programming approach for solving the rod cutting problem using 2D Table
   * @param prices Prices of rod of various sizes
   * @param size Size of the rod to be cut up
   * @return Maximum profit that can be derived from cutting the rod into various sizes
   */
  public static int cutRodDP2(int[] prices, int size) {
    int[][] dp = new int[prices.length + 1][size + 1];
    
    for(int i=1; i <= prices.length; i++) {
        for(int rodSize = 1; rodSize <= size; rodSize++) {
            if(rodSize >= i) {
                dp[i][rodSize] = Math.max(dp[i-1][rodSize], prices[i] + dp[i][rodSize - i]);
            } else {
                dp[i][rodSize] = dp[i-1][rodSize];
            }
        }
    }
    
    return dp[prices.length][size];
  }
}
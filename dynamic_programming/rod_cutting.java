/**
 Given a iron rod of certain length and the price of selling rods of different 
 lengths in the market, how would we cut the rod so that the profit is maximized?

 For example, let us say the price of rods of different lengths in the market
 is as given below.

          Length        Price
               1 ---->  1
               2 ---->  5
               3 ---->  8
               4 ---->  9
               5 ---->  10
               6 ---->  17
               7 ---->  17
               8 ---->  20

 If we have a rod of length 4, then selling the rod as it is will bring us a profit
 of 9 dollars. Where as if we cut it into two pieces of length 2 each, then the two
 pieces will be sold for 5 dollars each bring in 10 USD. (5+5=10). Hence it's a good 
 idea to cut the rod into 2 pieces rather than selling it as a whole.

 But we're still not sure if cutting it into two pieces is the most optimal solution
 or not because we have not seen all possible values. Since we're cutting the rod
 into integer lengths only, the table below shows all possible ways of cutting the 
 rod and the cost of that combination in the market.

        Length of Each Part                Total Market Value
                        4     - - - ->       9
                     1, 3     - - - ->       9 (1+8)
                  1, 1, 2     - - - ->       7 (1+1+ 5)
                     2, 2     - - - ->       10 (5+5)
               1, 1, 1, 1     - - - ->       4 (1+1+1+1)

 From the table above, it's clear that cutting the rod into two equal pieces of
 length 2 gives us the maximum value.

*/

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

    System.out.println("Dynamic Programming 2");
		System.out.println(cutRodDP2(price, rod_len1));
		System.out.println(cutRodDP2(price2, rod_len2));
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
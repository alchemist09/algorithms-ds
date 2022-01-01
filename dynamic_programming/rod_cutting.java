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
}
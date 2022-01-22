package dynamic_programming;

class Knapsack {
  public static void main(String[] args) {
    int[] weights01 = {2, 3, 5, 7, 10};
		int[] values01 = {15, 5, 6, 11, 4};
		int capacity = 15;
		
		int[] weights02 = {2, 3, 5, 7};
		int[] values02 = {16, 5, 9, 6};
		int capacity2 = 9;
		
		int[] weights03 = {2, 3, 4, 5};
    int[] values03  = {3, 4, 5, 6};
    int capacity3 = 9;

    System.out.println("Brute Force:");
		System.out.println(knapSackBF(capacity, weights01, values01, weights01.length));
		System.out.println(knapSackBF(capacity2, weights02, values02, weights02.length));
		System.out.println(knapSackBF(capacity3, weights03, values03, weights03.length));
  }

  /**
	 * @param capacity The maximum capacity of the backpack
	 * @param weights  Array holding the weights of individuals items
	 * @param values   Array holding the value associated with each item
	 * @param n        The number of items available
	 * @return         The maximum value that can be packed into the backpack
	 */
  public static int knapSackBF(int capacity, int[] weights, int[] values, int n) {
    // if backpack is full or there are no more items left, return zero 
    if(capacity <= 0 || n <= 0) {
        return 0;
    }
    
    // if current item exceeds capacity of backpack, skip it 
    if(weights[n-1] > capacity) {
        return knapSackBF(capacity, weights, values, n-1);
    }
    
    // case where we include the current item 
    int x = values[n-1] + knapSackBF(capacity - weights[n-1], weights, values, n-1);
    
    // case where we exclude the current item 
    int y = knapSackBF(capacity, weights, values, n-1);
    
    return Math.max(x, y);
  }

  /**
   * Top-down Memoization solution for solving 0/1 Knapsack problem
	 * @param capacity The maximum capacity of the backpack
	 * @param weights  Array holding the weights of individuals items
	 * @param values   Array holding the value associated with each item
	 * @param n        The number of items available
	 * @return         The maximum value that can be packed into the backpack
	 */
	public static int knapSackMemo01(int capacity, int[] weights, int[] values, int n) {
    if(capacity == 0 || n == 0) {
        return 0;
    }
    
    if(weights == null || weights.length == 0 || values == null || values.length == 0) {
        return 0;
    }
    
    int[][] cache = new int[n+1][capacity + 1];
    return knapSackUtil01(capacity, weights, values, n, cache);
  }

  /**
   * Recursive helper function that helps with the memoization solution
	 * @param capacity The maximum capacity of the backpack
	 * @param weights Array holding the weights of individuals items
	 * @param values  Array holding the value associated with each item
	 * @param n       The number of items available
	 * @return        The maximum value that can be packed into the backpack
	 */
	private static int knapSackUtil01(int capacity, int[] weights, int[] values, int n, int[][]cache) {
    // no more capacity left or we've exhausted the number of items
    if(capacity <= 0 || n <= 0) {
        return 0;
    }
    
    // if maximum value for a certain capacity and number of items is already computed, return zero
    if(cache[n][capacity] > 0) {
        return cache[n][capacity];
    }
    
    // case where current item is greater than backpack capacity - skip the item
    if(weights[n-1] > capacity) {
        return knapSackUtil01(capacity, weights, values, n-1, cache);
    } 
    
    // case where we choose to include current item 
    int x = values[n-1] + knapSackUtil01(capacity - weights[n-1], weights, values, n-1, cache);
    
    // case where we choose to exclude current item
    int y = knapSackUtil01(capacity, weights, values, n-1, cache);
    
    cache[n][capacity] = Math.max(x, y);
    
    return cache[n][capacity];
  }
}
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
}
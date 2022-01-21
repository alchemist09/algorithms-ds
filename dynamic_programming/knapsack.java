class Knapsack {
  public static void main(String[] args) {
    
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
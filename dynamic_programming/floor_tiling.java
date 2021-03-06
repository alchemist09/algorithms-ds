/**
 * Given an empty plot of size 2 x n. We want to place tiles such that the plot is
 * entirely covered. Each tile is of size 2 x 1 and can either be placed vertically
 * or horizontally.
 * 
 * Write a function that accepts n as input and returns the total number of ways
 * in which we can place the tiles (without breaking the tiles).
 */

package dynamic_programming;

import java.util.Arrays;

class FloorTiling {
  public static void main(String[] args) {
    System.out.println("Floor Tiling Brute Force: " + floorTiling(10));
    System.out.println("Floor Tiling Memoization: " + floorTilingMemo(10));
    System.out.println("Floor Tiling DP - O(N) Space: " + floorTilingDP(10));
    System.out.println("Floor Tiling DP - O(1) Space: " + floorTilingDP2(10));
  }

  /**
   * Brute force approach of calculating the number of ways to tile a floor
   * @param floor_length
   * @return Count of unique ways to fit tiles in floor
   */
  public static long floorTiling(int floor_length) {
    if(floor_length == 0) { return 0; }
	  if(floor_length == 1) { return 1; }
	  if(floor_length == 2) { return 2; }
	  return floorTiling(floor_length - 1) + floorTiling(floor_length - 2);
  }

  /**
   * Memoization approach of calculating ways to tile a 2xN floor
   * @param floor_length Length of the floor
   * @return Count of unique ways to fit tiles on floor
   */
  public static long floorTilingMemo(int floor_length) {
    long[] memo = new long[floor_length + 1];
    Arrays.fill(memo, -1);
    return floorTilingUtil(memo, floor_length);
  }

  /**
   * Recursively calculates way to fit tiles in floor of specified size
   * @param cache Lookup table for storing overlapping subproblems
   * @param N Length of floor
   * @return No. of unique ways to tile floor
   */
  private static long floorTilingUtil(long[] cache, int N) {
    if(N <= 0) { return 0; }
    if(N == 1) { return 1; }
    if(N == 2) { return 2; }
    
    if(cache[N] != -1) {
      return cache[N];
    }
    
    cache[N] = floorTilingUtil(cache, N-2) + floorTilingUtil(cache, N-1);
    return cache[N];
  }

  /**
   * Bottom-Up Dynamic Programming approach with O(n) Space
   * @param floor_length Length of the floor
   * @return Count of unique ways to place tiles on the floor
   */
  public static long floorTilingDP(int floor_length) {
    long[] num_ways = new long[floor_length + 1];
    num_ways[1] = 1;
    num_ways[2] = 2;
    
    for(int length=3; length <= floor_length; length++) {
        num_ways[length] = num_ways[length - 1] + num_ways[length - 2];
    }
    
    return num_ways[floor_length];
  }

  /**
   * Bottom-Up Dynamic Programming approach with O(1) space
   * @param floor_length Length of the floor
   * @return Count of unique ways to tile the floor
   */
  public static long floorTilingDP2(int floor_length) {
    int a = 1, b = 2; // initial values for floor length 1 and 2
    int c=0; // count unique ways
	  
	  for(int len=3; len <= floor_length; len++) {
      c = a + b;
      a = b;
      b = c;
	  }

	  return c;
  }
}
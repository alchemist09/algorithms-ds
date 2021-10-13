package dynamic_programming;

import java.util.Arrays;

class FloorTiling {
  public static void main(String[] args) {
    System.out.println("Floor Tiling Brute Force: " + floorTiling(10));
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
}
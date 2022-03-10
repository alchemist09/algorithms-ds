package dynamic_programming;

class EggDrop {
  public static void main(String[] args) {
    
  }

  /**
   * Brute Force Solution to Egg Dropping Problem
   * @param eggs - The number of eggs available
   * @param floors - The number of floors to test out the floor breaking egg
   * @return The minimum no. of drops to determiine floor that breaks egg
   */
  public static int eggDropBF(int eggs, int floors) {
    // if we've only one egg or zero or one floors,
    // return the number of floors
    if(eggs == 1 || floors == 0 || floors == 1) {
        return floors;
    }
    
    int minDrops = Integer.MAX_VALUE;
    
    for(int curr_floor = 1; curr_floor <= floors; curr_floor++) {
        int temp = Math.max(
                // case where we break an egg
                eggDropBF(eggs - 1, curr_floor - 1),
                // case where we do not break an egg
                eggDropBF(eggs, floors - curr_floor)
            );
            
       if(temp < minDrops) {
           minDrops = temp;
       }
    }
    
    return minDrops + 1;
  }
}
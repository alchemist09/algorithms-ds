package dynamic_programming;

import java.util.Arrays;

class EggDrop {
  public static void main(String[] args) {
    int num_eggs1 = 2;
		int num_floors1 = 6;
		
		int num_eggs2 = 3;
		int num_floors2 = 14;
		
		int num_eggs3 = 2;
		int num_floors3 = 10;

    int num_eggs4 = 3;
		int num_floors4 = 26;
		
		int num_eggs5 = 2;
		int num_floors5 = 36;
		
		int num_eggs6 = 2;
		int num_floors6 = 50;

    System.out.println("Egg Drop - Brute Force: ");
		System.out.println(eggDropBF(num_eggs1, num_floors1));
		System.out.println(eggDropBF(num_eggs2, num_floors2));
		System.out.println(eggDropBF(num_eggs3, num_floors3));

    System.out.println("\nEgg Drop - Memoization: ");
		System.out.println(eggDropMemo(num_eggs1, num_floors1));
		System.out.println(eggDropMemo(num_eggs2, num_floors2));
		System.out.println(eggDropMemo(num_eggs3, num_floors3));
		System.out.println(eggDropMemo(num_eggs4, num_floors4));
		System.out.println(eggDropMemo(num_eggs5, num_floors5));
		System.out.println(eggDropMemo(num_eggs6, num_floors6));

    System.out.println("\nEgg Drop - Dynamic Programming: ");
		System.out.println(eggDropDP(num_eggs1, num_floors1));
		System.out.println(eggDropDP(num_eggs2, num_floors2));
		System.out.println(eggDropDP(num_eggs3, num_floors3));
		System.out.println(eggDropDP(num_eggs4, num_floors4));
		System.out.println(eggDropDP(num_eggs5, num_floors5));
		System.out.println(eggDropDP(num_eggs6, num_floors6));
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

  /**
   * Top-Down Memoization Solution to Egg Drop Problem
   * @param eggs The number of eggs available for the experiment
   * @param floors The number of floors from where egg drop experiments are conducted
   * @return The minimum no. of drops that determines the floor upon which an egg breaks
   */
  public static int eggDropMemo(int eggs, int floors) {
    if(eggs == 0 || floors == 0) {
        return 0;
    }
    
    int[][] memoTable = new int[eggs + 1][floors + 1];
    for(int i=0; i <= eggs; i++) {
        Arrays.fill(memoTable[i], Integer.MAX_VALUE);
    }
    
    return dropUtil(eggs, floors, memoTable);
  }

  /**
   * Recursive function that helps with the Memoization solution
   * @param eggs No. of eggs available for egg dropping experiment
   * @param floors No. of floors on building from which eggs is dropped
   * @param cache 2D array to store solution to already computed subproblems
   * @return Minimum drops to determine the floor which breaks an egg
   */
  private static int dropUtil(int eggs, int floors, int[][] cache) {
    if(eggs == 1 || floors == 0 || floors == 1) {
        return floors;
    }
    
    // if value already computed, return it
    if(cache[eggs][floors] != Integer.MAX_VALUE) {
        return cache[eggs][floors];
    }
    
    int minDrops = Integer.MAX_VALUE;
    
    for(int curr_floor = 1; curr_floor <= floors; curr_floor++) {
        int temp = Math.max(
                // case where egg breaks
                dropUtil(eggs-1, curr_floor - 1, cache),
                // case where egg doesn't break
                dropUtil(eggs, floors - curr_floor, cache)
            );
            
        if(temp < minDrops) {
            minDrops = temp;
            cache[eggs][floors] = minDrops + 1;
        }
    }
    
    return cache[eggs][floors];
  }

  /**
   * Bottom-Up Dynamic Programming Solution to Egg Dropping Problem
   * @param eggs No. of available eggs for egg dropping experiment
   * @param floors No. of floors on the building from which eggs are being dropped
   * @return Minimum no. of drops to determine floor from which an en egg breaks
   */
  public static int eggDropDP(int eggs, int floors) {
    if(eggs == 0 || floors == 0) {
      return 0;
    }
    
    int[][] dp = new int[eggs + 1][floors + 1];
    
    // if we have only one egg, number of drops in the worst case
    // is going to equal no. of floors
    for(int j=0; j <= floors; j++) {
      dp[1][j] = j;
    }
    
    for(int egg = 2; egg <= eggs; egg++) {
      for(int curr_floor = 1; curr_floor <= floors; curr_floor++) {
        dp[egg][curr_floor] = Integer.MAX_VALUE;
        // for every intermediate floor between the floor 1 and the current floor,
        // do egg drop attempts to determine the minimum no. of drops in the worst case
        // you need to to know the floor at which the egg breaks
        for(int drop_attempt=1; drop_attempt <= curr_floor; drop_attempt++) {
          int temp = 1 + Math.max(
            // case where egg breaks
            dp[egg - 1][drop_attempt - 1],
            // case where egg doesn't break
            dp[egg][curr_floor - drop_attempt]
          );

          dp[egg][curr_floor] = Math.min(temp, dp[egg][curr_floor]);
        }
      }
    }

    return dp[eggs][floors];
  }
}
package dynamic_programming;

class EggDrop {
  public static void main(String[] args) {
    int num_eggs1 = 2;
		int num_floors1 = 6;
		
		int num_eggs2 = 3;
		int num_floors2 = 14;
		
		int num_eggs3 = 2;
		int num_floors3 = 10;

    System.out.println("Egg Drop - Brute Force: ");
		System.out.println(eggDropBF(num_eggs1, num_floors1));
		System.out.println(eggDropBF(num_eggs2, num_floors2));
		System.out.println(eggDropBF(num_eggs3, num_floors3));
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
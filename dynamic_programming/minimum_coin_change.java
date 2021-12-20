package dynamic_programming;

class MinimumCoinChange {
  public static void main(String[] args) {
    int[] coins = {1, 2, 3};
    int change = 5;

    int[] coins2 = {1, 2, 5, 10, 12, 20, 50};
    int change2 = 65;

    System.out.println(coinChangeBF(coins, change));
    System.out.println(coinChange(coins, change));
    System.out.println(coinChange(coins2, change2));
  }

  public static int coinChangeBF(int[] coins, int change) {
    if(change == 0) {
      return 0;
    }

    int num_coins = Integer.MAX_VALUE;
    for(int i=0; i < coins.length; i++) {
      if(coins[i] <= change) {
        int temp = coinChangeBF(coins, change-coins[i]);
        if(temp + 1 < num_coins) {
          num_coins = temp + 1;
        }
      }
    }

    return num_coins;
  }

  /**
   * Top-Down Memoization method for solving the minimum coin change problen
   * @param coins The available denominations of coins
   * @param change The amount that should be given back as change
   * @return The least amount of coins that can be used to give back the target change
   */
  public static int coinChange(int[] coins, int change) {
    if(change == 0) {
        return 0;
    }
    
    int[] T = new int[change + 1];
    return coinChangeUtil(coins, change, T);
  }

  /**
   * Utility function that recursively calls itself while while calculating the minimum
   * amount of coins requiered to give out the specified amount of change
   * @param coins The denominations of available coins
   * @param change The target change that should be given out
   * @param cache Record that stores solutions to previously computed sub-problems
   * @return The minimum number of coins required to give out the specified change
   */
  private static int coinChangeUtil(int[] coins, int change, int[]cache) {
    if(change == 0) {
        return 0;
    }
    
    if(cache[change] > 0 || cache[change] == -1) {
        return cache[change];
    }
    
    int min_coins = Integer.MAX_VALUE;
     
    for(int i=0; i < coins.length; i++) {
      if(coins[i] <= change) {
        int num_coins = coinChangeUtil(coins, change - coins[i], cache);
        if(num_coins >= 0 && num_coins < min_coins) {
          min_coins = num_coins + 1;
        }
      }
    }
     
    min_coins = (min_coins == Integer.MAX_VALUE) ? -1 : min_coins;
    cache[change] = min_coins;
     
    return cache[change];
  }
}
package dynamic_programming;

class MinimumCoinChange {
  public static void main(String[] args) {
    int[] coins = {1, 2, 3};
    int change = 5;

    System.out.println(coinChangeBF(coins, change));
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
}
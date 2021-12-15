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
}
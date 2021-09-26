package dynamic_programming;

/* There are N stations in a route, starting from 0 to N-1. A train moves from 
* station (0) to the last station (N-1) in only forward direction. The cost of
* ticket between any two station is given. Find the minimum cost of travel between
* station (0) to station (N-1)
* 
* ***********************************************************************
* ****************************** SOLUTION *******************************
* ***********************************************************************
* 
* First you define data structure in which cost of ticket between stations is 
* stored. If we have four stations (0 to 3), and the cost of ticket is stored
* in a 4 * 4 matrix as below
* 
*    cost[][] = {
*      {0, 10, 75, 94},
*      {-1, 0, 35, 50},
*      {-1, -1, 0, 80},
*      {-1, -1, -1, 0}
*    } 
* 
* cost[i][j] is cost of station from station i to station j. Since we are not
* moving backwards, cost[i][j] does not make sense when i > j, and hence they 
* are all -1. If i==j, then we are at the same station where we want to go,
* therefore all the diagonal elements are zeros.
* 
* If we want to move from station-0 to station-2, the cheapest way is to take 
* the ticket of station-1 from station-0, then the ticket of station-2 from 
* station-1. This way the total cost of travel is USD45(10+35). If we take the
* direct ticket of station-2 from station-0, then the cost of travel is USD75.
* 
* If minCost(s, d) is the minimum cost of travelling from station-s to station-d,
* the minimum cost to reach N-1 from 0 can be recursively defined as 
* 
*         minCost(0, N-1) {
*            MIN {
*              cost[0][N-1],
*              minCost(0, 2) + minCost(2, N-1),
*              ... ... ... ...,
*              minCost(0, N-2) + cost[N-2][N-1]
*            } 
*         }
* 
* First option is to go directly to station N-1 from station-0 without any break.
* Second option is to break at station-i and so on. When we break at station-i, 
* we calculate the minimum cost of moving from 0-i, and then the minimum cost of
* moving from i to N-1.
*/

import java.util.Arrays;

class MinCostBtwn2Cities {
  public static void main(String[] args) {
    int[][] cost = {
			{ 0, 25, 20, 10, 105 },
      { 20, 0, 15, 80, 80 },
      { 30, 15, 0, 70, 90 },
      { 10, 10, 50, 0, 100 },
      { 40, 50, 5, 10, 0}
		};

    int[][] cost2 = {
			{0, 10, 75, 94},
			{-1, 0, 35, 50},
			{-1, -1, 0, 80},
			{-1, -1, -1, 0}
		};

    System.out.println(minCost(cost, 2, 4));
    System.out.println(minCostMemo(cost2, 0, 3));
    System.out.println(minCostDP(cost2, 3));
  }

  /**
   * Brute force approach of calculating the minimum cost between 2 ctities g
   * given a fare table
   * @param cost Matrix specifying the cost of travel between any 2 cities
   * @param s Source city of journey
   * @param d Destination of journey 
   * @return the minimum cost to travel from source city s, to destination city d
   */
  public static int minCost(int[][] cost, int s, int d) {
		if(s==d || s==d-1) {
			return cost[s][d];
		}
		
		int min_cost = cost[s][d];
		for(int i=s+1; i < d; i++) {
			// calculate the minimum cost between every intermediate station 
			// min_cost = minCost(s, i) + minCost(i, d) 
			int temp = minCost(cost, s, i) + minCost(cost, i, d);
			if(temp < min_cost) {
				min_cost = temp;
			}
		}
		return min_cost;
	}

  /**
   * Calculates the minimum cost of travel between 2 cities using Top-Down Memoization approach
   * @param cost The fair table presented as a 2D matrix
   * @param s Source of journey
   * @param d Destination of journey
   * @return Minimum cost of travel between s and d
   */
  public static int minCostMemo(int[][] cost, int s, int d) {
		// specify base cases 
		if(s==d || s+1==d) {
			return cost[s][d];
		}
		
		int N = cost.length;
		int[][] cache = new int[N][N];
		
		if(cache[s][d] == 0) {
			int min_cost = cost[s][d];
			for(int i=s+1; i < d; i++) {
				int temp = minCostMemo(cost, s, i) + minCostMemo(cost, i, d);
				if(temp < min_cost) {
					min_cost = temp;
				}
			}
			// store the minimum cost in cache 
			cache[s][d] = min_cost;
		}
		// otherwise return previously computed value
		return cache[s][d];
	}

  /**
   * Calculate the minimum cost of travel between 2 cities using bottom-up dynamic programming
   * @param cost 2D matrix representing the cost of travel between any 2 cities
   * @param d The last destination possible given the fare table
   * @return minimum cost of travel between any 2 cities
   */
  public static int minCostDP(int[][] cost, int d) {
		int N = cost.length;
		int[] min_cost = new int[N];
		min_cost[1] = cost[0][1];
		
		for(int i=2; i <= d; i++) {
			min_cost[i] = cost[0][i];
			// check if sum of minimum costs of all the intermediate 
			// stations is less than cost of going direct
			for(int j=1; j < i; j++) {
				if(min_cost[i] > min_cost[j] + cost[j][i]) {
					min_cost[i] = min_cost[j] + cost[j][i];
				}
			}
		}
		return min_cost[d];
	}
}
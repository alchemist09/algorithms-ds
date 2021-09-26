class MinCostBtwn2Cities {
  public static void main(String[] args) {
    
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
}
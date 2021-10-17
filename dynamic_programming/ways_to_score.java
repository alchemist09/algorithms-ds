package dynamic_programming;

class WaysToScore {
  public static void main(String[] args) {
    System.out.println("Ways to Score[Brute Force] 13: " + waysToScoreBF(13));

    int N = 13;
		int[] cache = new int[N + 1];
		System.out.println("Ways to Score[Memo] 13: " + waysToScoreMemo(cache, 13));
  }

  /**
   * Calculate ways to reach a specifed score using brute force approach
   * @param targetScore The score
   * @return unique count of ways to reach target score
   */
  public static int waysToScoreBF(int targetScore) {
    if(targetScore < 0) {
      return 0;
    }
    
    if(targetScore == 0) {
      return 1;
    }
    
    return waysToScoreBF(targetScore - 10) + waysToScoreBF(targetScore - 5) + waysToScoreBF(targetScore - 3);
  }

  /**
   * Calculate ways to reach target score using top-down memoization approach
   * @param scores Memoization table to store solutions to pre-computed problems
   * @param targetScore The score we're targeting
   * @return Unique count of ways to reach target score
   */
  public static int waysToScoreMemo(int[] scores, int targetScore) {
    if(targetScore < 0) { return 0; }
    if(targetScore == 0) { return 1; }
    
    if(scores[targetScore] != 0) {
        return scores[targetScore];
    }
    
    scores[targetScore] = waysToScoreMemo(scores, targetScore - 10) 
                        + waysToScoreMemo(scores, targetScore - 5) 
                        + waysToScoreMemo(scores, targetScore - 3);
   
   return scores[targetScore];
  }

  /**
   * Calculate ways to reach target score using bottom-up dynamic programming
   * @param targetScore The score being targeted
   * @return Unique count of ways to reach target score
   */
  public static int waysToScoreDP(int targetScore) {
    int[] scores = new int[targetScore + 1];
    scores[0] = 1;
    
    for(int i=1; i <= targetScore; i++) {
        if(i-3 >= 0) {
            scores[i] = scores[i-3] + scores[i];
        }
        
        if(i-5 >= 0) {
            scores[i] = scores[i-5] + scores[i];
        }
        
        if(i-10 >= 0) {
            scores[i] = scores[i-10] + scores[i];
        }
    }
    
    return scores[targetScore];
  }
}
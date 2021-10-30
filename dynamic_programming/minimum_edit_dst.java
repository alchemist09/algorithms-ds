package dynamic_programming;

class MinimumEditDistance {
  public static void main(String[] args) {
    String str1 = "CAT";
		String str2 = "CARE";
		System.out.println(minEditDistance(str1, str2));
  }

  public static int minEditDistance(String str1, String str2) {
    // if str1 is empty or null, we need to insert characters of str2
    if(str1 == null || str1.length() == 0) {
      return str2.length();
    }

    // if str2 is empty or null, we need to delete all characters of str1
    if(str2 == null || str2.length() == 0) {
      return str1.length();
    }

    // if first characters of both strings are the same, then ignore it
    // and find the edit distance between the remaining characters
    if(str1.charAt(0) == str2.charAt(0)) {
      return minEditDistance(str1.substring(1), str2.substring(1));
    }

    // find the edit distance of all three operations
    int d, u, i = 0;

    d = minEditDistance(str1.substring(1), str2);
    u = minEditDistance(str1.substring(1), str2.substring(1));
    i = minEditDistance(str1, str2.substring(1));

    // return minimum of the three values + 1
    return getMin(d, u, i) + 1;
  }

  public static int getMin(int x, int y, int z) {
    return (x < y) ? ((x < z) ? x : z) : ((y < z) ? y : z );
  }
}
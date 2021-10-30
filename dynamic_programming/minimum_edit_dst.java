package dynamic_programming;

class MinimumEditDistance {
  public static void main(String[] args) {
    
  }

  public static int minEditDistance(String str1, String str2) {
    // if str1 is empty or null, we need to insert characters of str2
    if(str1 == null || str1 == "") {
      return str2.length();
    }

    // if str2 is empty or null, we need to delete all characters of str1
    if(str2 == null || str2 == "") {
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
    return Math.min(d, u, i) + 1;
  }
}
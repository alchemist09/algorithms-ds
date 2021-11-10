package dynamic_programming;

class StringInterleave {
  public static void main(String[] args) {
    String str1 = "aabcc";
		String str2 = "dbbca";
		String str3 = "aadbbcbcac";

    String str4 = "abc";
		String str5 = "def";
		String str6 = "dcabef";
    
    System.out.println(isInterleaveBF(str1, str2, str3));
    System.out.println(isInterleaveBF(str4, str5, str6));
  }

  public static boolean isInterleaveBF(String s1, String s2, String s3) {
    // if all three strings are empty return true
    if(s1.length() == 0 && s2.length() == 0 && s3.length() == 0) {
        return true;
    }
    
    // if s3 is empty, s1 or s2 or both are not empty
    if(s3.length() == 0) {
        return false;
    }
    
    // if both s1 and s2 are empty but s3 is not
    if(s1.length() == 0 && s2.length() == 0) {
        return false;
    }
    
    boolean first, second, third;
    first = second = third = false;
    
    if((s1.length() != 0 && s1.charAt(0) == s3.charAt(0)) && (s2.length() != 0 && s2.charAt(0) == s3.charAt(0))) {
        third = isInterleaveBF(s1.substring(1), s2, s3.substring(1)) || isInterleaveBF(s1, s2.substring(1), s3.substring(1));
    }
        
    if(s1.length() != 0 && (s1.charAt(0) == s3.charAt(0))) {
        first = isInterleaveBF(s1.substring(1), s2, s3.substring(1));
    }
    
    if(s2.length() != 0 && (s2.charAt(0) == s3.charAt(0))) {
        second = isInterleaveBF(s1, s2.substring(1), s3.substring(1));
    }
    
    return (first || second || third);
  }
}
package dynamic_programming;

import java.util.Arrays;

class SubsetSum {
  public static void main(String[] args) {
    
  }

  public static boolean isSubsetSum(int[] arr, int target) {
    // a matching subset has been found
    if(target == 0) {
        return true;
    }
    
    // empty array has no values that can add up to the target value
    if(arr.length == 0) {
        return false;
    }
    
    // if the current element being examined is greater than target sum, check rest of the array
    if(arr[0] > target) {
        return isSubsetSum(Arrays.copyOfRange(arr, 1, arr.length), target);
    }
    
    // either include or exclude current item in subset
    return isSubsetSum(arr, target - arr[0]) || isSubsetSum(Arrays.copyOfRange(arr, 1, arr.length), target);
}
}
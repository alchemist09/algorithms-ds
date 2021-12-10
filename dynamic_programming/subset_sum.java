/**
Given an array of non-negative integers and a positive number X, determine if 
there exists a subset of elements of array with sum equal to X.

For example:
           Input array: {3, 2, 7, 1}    X = 6
           Output: True  // because sum of {3, 2, 1} is 6
*/

package dynamic_programming;

import java.util.Arrays;

class SubsetSum {
  public static void main(String[] args) {
    int[] arr2 = {3, 2, 7, 1};
    int target = 6;

    System.out.println(isSubsetSum(arr2, target));
    System.out.println(isSubsetSumDP(arr2, target));
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

  public static boolean isSubsetSumDP(int[] arr, int target) {
    boolean[][] T = new boolean[arr.length + 1][target + 1];
    
    // fill leftmost column - case where target is zero, everything is true 
    for(int i=0; i < T.length; i++) {
        T[i][0] = true;
    }
    
    // fill topmost row - case where the target sum is zero, everything is false 
    for(int j=1; j <= target; j++) {
        T[0][j] = false;
    }
    
    // fill the rest of the cells 
    for(int i=1; i <= arr.length; i++) {
        for(int j=1; j <= target; j++) {
            if(j - arr[i-1] >= 0) {
                T[i][j] = T[i-1][j] || T[i-1][j - arr[i-1]];
            } else {
                T[i][j] = T[i-1][j];
            }
        }
    }
    
    return T[arr.length][target];
  }
}
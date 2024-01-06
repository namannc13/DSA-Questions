package DP.Questions2D.StringQuestions;

import java.util.Arrays;

public class longestCommonSubsequence {
    public static void main(String[] args) {
        char[] arr1 = {'a','b','c','d','e'};    
        char[] arr2 = {'a','c','e'}; 

        System.out.println(longestCommonSubsequenceRecursion(arr1, arr2, arr1.length-1, arr2.length-1));
        // System.out.println(longestCommonSubsequenceRecursion(arr1, arr2, arr1.length, arr2.length)); // AFTER INDEX SHIFTING TO RIGHT BY ONCE

        int[][] dp = new int[arr1.length][arr2.length];
        // int[][] dp = new int[arr1.length+1][arr2.length+1]; // AFTER INDEX SHIFTING TO RIGHT BY ONCE
        for(int i =0; i< dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println(longestCommonSubsequenceRecursionDP(arr1, arr2, arr1.length-1, arr2.length-1,dp));
        // System.out.println(longestCommonSubsequenceRecursionDP(arr1, arr2, arr1.length, arr2.length,dp)); // AFTER INDEX SHIFTING TO RIGHT BY ONCE

        int[][] dp2 = new int[arr1.length+1][arr2.length+1]; // AFTER INDEX SHIFTING TO RIGHT BY ONCE
        for(int i =0; i< dp2.length; i++){
            Arrays.fill(dp2[i], 0);
        }
        System.out.println("After Shifting index -> " + longestCommonSubsequenceTabulationAfterIndexShifting(arr1, arr2, dp2));

        for(int i =0; i< dp2.length; i++){
            System.out.println(Arrays.toString(dp2[i]));
        }
    }

    private static int longestCommonSubsequenceRecursionDP(char[] arr1, char[] arr2, int index1, int index2, int[][] dp) {
        if(index1 < 0 || index2 < 0) return 0; // if(index1 == 0 || index2 == 0) return 0; // AFTER INDEX SHIFTING TO RIGHT BY ONCE

        if(dp[index1][index2] != -1) return dp[index1][index2];

        if(arr1[index1] == arr2[index2]) return dp[index1][index2] = 1 + longestCommonSubsequenceRecursionDP(arr1, arr2, index1-1, index2-1,dp); // match 
        // if(arr1[index1-1] == arr2[index2-1]) return dp[index1][index2] = 1 + longestCommonSubsequenceRecursionDP(arr1, arr2, index1-1, index2-1,dp); // match // AFTER INDEX SHIFTING TO RIGHT BY ONCE
        else return dp[index1][index2] = 0 + Math.max(longestCommonSubsequenceRecursionDP(arr1, arr2, index1-1, index2,dp), longestCommonSubsequenceRecursionDP(arr1, arr2, index1, index2-1,dp)); // not match
    }

    private static int longestCommonSubsequenceRecursion(char[] arr1, char[] arr2, int index1, int index2) {
        if(index1 < 0 || index2 < 0) return 0; // if(index1 == 0 || index2 == 0) return 0; // AFTER INDEX SHIFTING TO RIGHT BY ONCE

        if(arr1[index1] == arr2[index2]) return 1 + longestCommonSubsequenceRecursion(arr1, arr2, index1-1, index2-1); // match
        // if(arr1[index1-1] == arr2[index2-1]) return 1 + longestCommonSubsequenceRecursion(arr1, arr2, index1-1, index2-1); // match // AFTER INDEX SHIFTING TO RIGHT BY ONCE
        else return 0 + Math.max(longestCommonSubsequenceRecursion(arr1, arr2, index1-1, index2), longestCommonSubsequenceRecursion(arr1, arr2, index1, index2-1)); // not match
    }

    // for index shifting

    // 1. inc dp size by [+1][+1]
    // 2. i <= arr.length in every loop now ( <= )
    // 3. where arr mentioned => do it like this -> arr[i-1][j-1] ( normal would have been arr[i][j] )
    public static int longestCommonSubsequenceTabulationAfterIndexShifting(char[] arr1, char[] arr2, int[][] dp){
        // for(int i=0; i<= arr2.length; i++){
        //     dp[0][i] = 0;
        // }
        // for(int i=0; i<= arr1.length; i++){
        //     dp[i][0] = 0;
        // }
        for(int i =1; i<= arr1.length; i++){
            for(int j=1; j<= arr2.length; j++){
                if(arr1[i-1] == arr2[j-1]) dp[i][j] = 1 + dp[i-1][j-1]; // match
                else dp[i][j] = 0 + Math.max(dp[i-1][j],dp[i][j-1]); // not match
            }
        }
        return dp[arr1.length][arr2.length];
    }
}

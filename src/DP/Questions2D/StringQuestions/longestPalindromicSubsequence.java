package DP.Questions2D.StringQuestions;

import java.util.Arrays;

public class longestPalindromicSubsequence {
    public static void main(String[] args) {
        char[] arr1 = {'b','b','a','b','c','b','c','a','b'};
        char[] arr2 = {'b','a','c','b','c','b','a','b','b'};

        System.out.println(longestPalindromicSubsequenceTabulationHelper(arr1, arr2)); 
    }

    //To calculate the longest Palindromic Subsequence, just find the longest common subsequence between the arr and the reverse of that arr itself !!
    private static int longestPalindromicSubsequenceTabulationHelper(char[] arr1, char[] arr2) {
        int[][] dp = new int[arr1.length+1][arr2.length+1]; // AFTER INDEX SHIFTING TO RIGHT BY ONCE
        for(int i =0; i< dp.length; i++){
            Arrays.fill(dp[i], 0);
        }
        return longestCommonSubsequence.longestCommonSubsequenceTabulationAfterIndexShifting(arr1,arr2,dp);
    }
}

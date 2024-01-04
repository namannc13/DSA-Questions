package DP.Questions2D.StringQuestions;

import java.util.Arrays;

public class minimumInsertionstoConvertStringAtoStringB {
    public static void main(String[] args) {
        char[] arr1 = {'a','b','c','d'};
        char[] arr2 = {'a','n','c'};

        System.out.println(minimumInsertionstoConvertStringAtoStringBTabulationHelper(arr1,arr2));
    }

    private static int minimumInsertionstoConvertStringAtoStringBTabulationHelper(char[] arr1, char[] arr2) {
        // we will find the longest common subsequence, then the characters left in arr1 after subtracting the length of the array to the longest common subsequence's length, we get the number of deletions we need to make, and the characters left in arr2 after subtracting the length of the array to the longest common subsequence's length , we get the number of insertions we need to make
        // deletions = arr1.length - lcs
        // insertions = arr2.length - lcs
        // answer = deletions + insertions
        // answer = arr1.length - lcs + arr2.length - lcs
        // answer = arr1.length + arr2.length - 2lcs
        int[][] dp = new int[arr1.length+1][arr2.length+1]; // AFTER INDEX SHIFTING TO RIGHT BY ONCE
        for(int i =0; i< dp.length; i++){
            Arrays.fill(dp[i], 0);
        }
        return arr1.length + arr2.length - (2* longestCommonSubsequence.longestCommonSubsequenceTabulationAfterIndexShifting(arr1, arr2, dp));
    }
}

package DP.Questions2D.StringQuestions;

import java.util.Arrays;

public class printLongestCommonSubsequence {
    public static void main(String[] args) {
        char[] arr1 = {'a','b','c','d','e'};    
        char[] arr2 = {'b','d','g','e','k'}; 

        int[][] dp = new int[arr1.length+1][arr2.length+1]; // AFTER INDEX SHIFTING TO RIGHT BY ONCE
        for(int i =0; i< dp.length; i++){
            Arrays.fill(dp[i], 0);
        }
        System.out.println(printLongestCommonSubsequenceTabulation(arr1, arr2, dp));
    }

    private static String printLongestCommonSubsequenceTabulation(char[] arr1, char[] arr2, int[][] dp) {
        for(int i=0; i<= arr2.length; i++){
            dp[0][i] = 0;
        }
        for(int i=0; i<= arr1.length; i++){
            dp[i][0] = 0;
        }

        for(int i =1; i<= arr1.length; i++){
            for(int j=1; j<= arr2.length; j++){
                if(arr1[i-1] == arr2[j-1]) dp[i][j] = 1 + dp[i-1][j-1]; // match
                else dp[i][j] = 0 + Math.max(dp[i-1][j],dp[i][j-1]); // not match
            }
        }

        for(int i =0; i< dp.length; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        // [0, 0, 0, 0]
        // [0, 1, 1, 1]
        // [0, 1, 1, 1]
        // [0, 1, 2, 2]
        // [0, 1, 2, 2]
        // [0, 1, 2, 3]
        // we have the array so now we will start from the last index and go bottom up to find the string
        
        char[] answer = new char[dp[arr1.length][arr2.length]];
        int index = answer.length-1;
        int i = arr1.length, j = arr2.length;
        while( i>0 && j>0) {
            if(arr1[i-1] == arr2[j-1]){
                answer[index] = arr1[i-1];
                index--;
                i--;
                j--;
            } 
            else{
                if(dp[i-1][j] > dp[i][j-1]){
                    i--;
                }else{
                    j--;
                }
            }
        }
        return Arrays.toString(answer);
    }
}
